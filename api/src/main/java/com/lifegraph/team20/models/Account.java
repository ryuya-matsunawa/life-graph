package com.lifegraph.team20.models;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue
	// usersテーブルのid
	private int id;

	private int user_id;

	@NotBlank
	// usersテーブルのusername
	private String username;

	@NotBlank
	// rolesテーブルのname
	private String name;

	private Timestamp created_at;

	private Timestamp updated_at;

}