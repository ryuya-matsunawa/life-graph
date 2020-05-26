package com.lifegraph.team20.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.models.Password;

@Repository
public class PasswordRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Boolean existByIdPassword(Password user) {
    final String sql = "select count(*) from users where password = " + "(select password from users where id = 1)"
        + " and id = "
        + user.getUserId();

    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

    return 1 <= count;
  }

  public void changePassword(Password user) {

    jdbcTemplate.update("update users set password = '" + user.getNewPassword() + "' where id = "
        + user.getUserId());

  }

}
