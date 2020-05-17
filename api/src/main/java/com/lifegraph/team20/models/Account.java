package com.lifegraph.team20.models;

import lombok.Data;

@Data
public class Account {
	private int id;
	private String username;
	private String name;

	public Account(int id, String username, String name) {
		this.id = id;
		this.username = username;
		this.name = name;
	}
}
