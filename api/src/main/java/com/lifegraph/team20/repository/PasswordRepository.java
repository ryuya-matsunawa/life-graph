package com.lifegraph.team20.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.models.Password;

@Repository
public class PasswordRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void changePassword(Password user) {

    jdbcTemplate.update("update users set password = '" + user.getNewPassword() + "' where id = "
        + user.getUserId());

  }

}
