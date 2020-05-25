package com.lifegraph.team20.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

//HTTPリクエストのRequestBodyに送るデータ
@Data
public class ChildGraphData {

  private long id;

  @NotNull
  private long userId;

  @NotNull
  @Max(99)
  @Min(0)
  private int age;

  @NotNull
  @Max(100)
  @Min(-100)
  private int score;
  //うちはここのNotNull制約、つけてなかったと思う。
  //  @NotNull
  @Size(max = 200, min = 1)
  private String comment;
}
