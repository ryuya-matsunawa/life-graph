package com.lifegraph.team20.repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.lifegraph.team20.models.ChildGraphData;

@Repository
public class ParentGraphRepository {

  @Autowired
  // MySQLのデータを持ってくるライブラリ
  private JdbcTemplate jdbcTemplate;

  // user_idが存在するかを確認したい
  // リクエストを送った人のuser_idのレコードをDBから取得する

  /**
   * 親グラフをユーザーIDを元に検索する ※ユーザーIDはユニークキー
   *
   * @param parentId ユーザーID
   * @return 親グラフ
   */

  public Optional<ChildGraphData> findByUserId(long userId) {
    final String sql = "select * from parent_graphs where user_id = " + userId;
    //	    List<ParentGraphData> parentLifeGraphs = jdbcTemplate.queryForList(sql, ParentGraphData.class);
    RowMapper<ChildGraphData> mapper = new BeanPropertyRowMapper<ChildGraphData>(ChildGraphData.class);
    List<ChildGraphData> parentLifeGraphs = jdbcTemplate.query(sql, mapper);
    return CollectionUtils.isEmpty(parentLifeGraphs) ? Optional.empty() : Optional.of(parentLifeGraphs.get(0));
  }

  public long insert(long userId) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("parent_graphs").usingGeneratedKeyColumns("id");
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("user_id", userId);
    parameters.put("created_at", timestamp);
    parameters.put("updated_at", timestamp);
    // execute insert
    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    // convert Number to Int using ((Number) key).intValue()
    return key.longValue();
  }

  public void updateParent(long userId) {
    String sql = "update parent_graphs set updated_at = now() where user_id = " + userId;
    jdbcTemplate.update(sql);
  }
}