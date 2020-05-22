package com.lifegraph.team20.models;

import javax.validation.constraints.NotNull;

import lombok.Data;

//HTTPリクエストを受け取るためのクラスここに情報の定義が集まっている
@Data
public class ParentGraphData {
	@NotNull
	private long userId;
}
