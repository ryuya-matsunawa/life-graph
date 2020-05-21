//梶｜作成・必要
package com.lifegraph.team20.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChildGraphRepository {

	// データベースと接続する
	@Autowired

	// MySQLのデータを持ってくるライブラリ
	JdbcTemplate jdbcTemplate;

	public Boolean existsByUserIdAndAge(long userId, int age) {
		final String parentId = "( select id from parent_graphs where user_id = " + userId + ") ";
		final String sql = "select count(( parent_id = " + parentId + " and age = " + age
				+ " ) or null) from child_graphs";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return 1 <= count;
	}

	// ageが重複しているか確認したい
	// リクエストできたageが同じユーザーのレコードに既に存在しているか
	public Integer selectChild(int age) {
		final String sql = "select count(*) from child_graphs where age = " + age;
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	public void updateChild(long id, int age, int score, String comment) {
		jdbcTemplate.update("update child_graphs set age = " + age + ", score = " + score + ", comment = '" + comment
				+ "' where parent_id = " + id);
	}

	// 子テーブルにレコードを追加する
	public void addChild(long userId, int age, int score, String comment) {
		final String parentId = "( select id from parent_graphs where user_id = " + userId + ") ";
		jdbcTemplate.update(
				"insert into child_graphs (parent_id, age, score, comment) values (" + parentId + "," + age + ","
						+ score + ",'" + comment + "')");
	}
}
