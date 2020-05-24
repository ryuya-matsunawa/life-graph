package com.lifegraph.team20.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GraphItemDeletRepositry {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  // レコードをidで削除する
  public void deletById(long id) {
    jdbcTemplate.update("DELETE FROM child_graphs WHERE id = " + id);
  }
}
