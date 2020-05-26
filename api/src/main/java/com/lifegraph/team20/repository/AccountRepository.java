package com.lifegraph.team20.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.models.Account;

@Repository
public class AccountRepository {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  public Account selectAccount(Integer id) {
    // 三つのテーブルくっつけてる。ユーザ名と権限名を取得するため
    final String sql = "select parent_graphs.id, username, name, created_at, updated_at from users \n"
        + "inner join user_roles on users.id = user_roles.user_id \n"
        + "inner join roles on roles.id = user_roles.role_id \n"
        + "left outer join parent_graphs on users.id = parent_graphs.user_id where users.id = :id;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
    Account result = jdbcTemplate.queryForObject(sql, param, new RowMapper<Account>() {
      public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println(rs.getString("created_at"));
        System.out.println(rs.getTimestamp("created_at"));
        System.out.println(toLocalDateTime(rs.getString("created_at")));
        return new Account(rs.getInt("id"), rs.getString("username"), rs.getString("name"),
            rs.getTimestamp("created_at"), toLocalDateTime(rs.getString("updated_at")));
      }
    });
    return result;
  }

  private LocalDateTime toLocalDateTime(String strTimestamp) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date formatDate = null;
    try {
      formatDate = sdf.parse(strTimestamp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return LocalDateTime.ofInstant(formatDate.toInstant(), ZoneId.of("UTC"));
  }
}
