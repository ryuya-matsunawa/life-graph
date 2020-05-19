package com.lifegraph.team20.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.UserData;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/life-graphs")
public class LifeGraphsContorller {
@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<UserData> userData() {
		List<UserData> userDatas = setUserData();
		return userDatas;
	}

	@Autowired

	private JdbcTemplate jdbcTemplate;

	private List<UserData> setUserData() {
		final String sql = "select * from users inner join parent_graphs on users.id = parent_graphs.user_id";
		return jdbcTemplate.query(sql, new RowMapper<UserData>() {
			public UserData mapRow(ResultSet rs, int rowNum) throws SQLException{
				return new UserData(rs.getInt("id"), rs.getString("username"), rs.getTimestamp("updated_at"));
			}
		});
	}
}