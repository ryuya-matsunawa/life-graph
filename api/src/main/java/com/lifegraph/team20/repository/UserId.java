package com.lifegraph.team20.repository;

import lombok.Data;

@Data

public class UserId {

	private long userId;


	public UserId(long userId){
		this.userId = userId;
	}

}
