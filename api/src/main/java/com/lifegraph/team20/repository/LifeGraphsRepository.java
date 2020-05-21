package com.lifegraph.team20.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.models.LifeGraph;
import com.lifegraph.team20.models.UserData;

@Repository
public class LifeGraphsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<UserData> getUserData() {
		final String sql = "select * from users inner join parent_graphs on users.id = parent_graphs.user_id";
		return jdbcTemplate.query(sql, new RowMapper<UserData>() {
			public UserData mapRow(ResultSet rs, int rowNum) throws SQLException{
				return new UserData(rs.getInt("id"), rs.getString("username"), rs.getTimestamp("updated_at"));
			}
		});
	}

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<LifeGraph> getGraph(Integer id) {
		//子グラフの中のparent_id, age, score, commentをparent_id = :idで照らし合わせて作る。
		//まず、SQLに詰め込んどくイメージ
		final String sql = "SELECT parent_id, age, score, comment FROM child_graphs WHERE parent_id = :id";
		//addValueを使うことで上のSQLの:idに下の右のidを入れている
		//param:idに数字を入れる役割、
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		//実行そのものはjdbcTemplate.queryでしてる。RowMapperは呪文
		List<LifeGraph> result = namedParameterJdbcTemplate.query(sql, param, new RowMapper<LifeGraph>() {
			//RowMappers使ったらmapRow使う呪文
			public LifeGraph mapRow(ResultSet rs, int rowNum) throws SQLException{
				//LifeGraphに定義してある、コンストラクタに入ってるものを呼び出してくる。そして、それをresultに入れてる
				return new LifeGraph(rs.getInt("parent_id"), rs.getInt("age"), rs.getInt("score"),rs.getString("comment"));
			}
			//resultの定義終了
		});
		return result;
	}
}