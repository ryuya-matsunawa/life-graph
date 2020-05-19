package com.lifegraph.team20.models;

import lombok.Data;

@Data
public class UserData {
	int id;
	String username;
	java.sql.Timestamp updated_at;

	public UserData(int id, String username, java.sql.Timestamp updated_at) {
		this.id = id;
		this.username = username;
		this.updated_at = updated_at;
	}
}