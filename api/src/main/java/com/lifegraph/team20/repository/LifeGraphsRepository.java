package com.lifegraph.team20.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.lifegraph.team20.models.LifeGraph;
import com.lifegraph.team20.models.UserData;

@Repository
public class LifeGraphsRepository {

  private static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");
  private static final ZoneId ZONE_ID_JST = ZoneId.of("Asia/Tokyo");

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<UserData> getUserData() {
    final String sql = "select parent_graphs.id, users.username, parent_graphs.updated_at from users inner join parent_graphs on users.id = parent_graphs.user_id";
    return jdbcTemplate.query(sql, new RowMapper<UserData>() {
      public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserData(rs.getInt("id"), rs.getString("username"), toLocalDateTime(rs.getString("updated_at")));
      }
    });
  }

  private LocalDateTime toLocalDateTime(String strTimestamp) {
    if (StringUtils.isEmpty(strTimestamp)) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone(ZONE_ID_UTC));
    Date formatDate = null;
    try {
      formatDate = sdf.parse(strTimestamp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    LocalDateTime def = LocalDateTime.ofInstant(formatDate.toInstant(), ZONE_ID_UTC);
    ZonedDateTime jst = def.atZone(ZONE_ID_UTC).withZoneSameInstant(ZONE_ID_JST);
    return jst.toOffsetDateTime().toLocalDateTime();
  }

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<LifeGraph> getGraph(Integer parent_id) {
    // 子グラフの中のparent_id, age, score, commentをparent_id = :idで照らし合わせて作る。
    // まず、SQLに詰め込んどくイメージ
    final String sql = "SELECT id, parent_id, age, score, comment FROM child_graphs WHERE parent_id = :parent_id";
    // addValueを使うことで上のSQLの:idに下の右のidを入れている
    // param:idに数字を入れる役割、
    SqlParameterSource param = new MapSqlParameterSource().addValue("parent_id", parent_id);
    // 実行そのものはjdbcTemplate.queryでしてる。RowMapperは呪文
    List<LifeGraph> result = namedParameterJdbcTemplate.query(sql, param, new RowMapper<LifeGraph>() {
      // RowMappers使ったらmapRow使う呪文
      public LifeGraph mapRow(ResultSet rs, int rowNum) throws SQLException {
        // LifeGraphに定義してある、コンストラクタに入ってるものを呼び出してくる。そして、それをresultに入れてる

        return new LifeGraph(rs.getInt("id"), rs.getInt("parent_id"), rs.getInt("age"), rs.getInt("score"),
            rs.getString("comment").equals("null") ? null : rs.getString("comment"));
      }
      // resultの定義終了
    });

    return result;
  }
}
