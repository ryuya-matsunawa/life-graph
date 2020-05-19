package com.lifegraph.team20.models;

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
  private int id;

  @NotBlank
  private String username;

  @NotBlank
  private String name;

}