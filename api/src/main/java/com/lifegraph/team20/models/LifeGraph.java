package com.lifegraph.team20.models;

import lombok.Data;

@Data

public class LifeGraph {
  private int id;
  private int parent_id;
  private int age;
  private int score;
  private String comment;

  public LifeGraph(int id, int parent_id, int age, int score, String comment) {
    this.id = id;
    this.parent_id = parent_id;
    this.age = age;
    this.score = score;
    this.comment = comment;
  }
}
