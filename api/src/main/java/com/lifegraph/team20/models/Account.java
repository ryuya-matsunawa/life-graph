package com.lifegraph.team20.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "users")
public class Account {

  @Id
  @GeneratedValue
  private int id;

  @NotBlank
  private String username;

  @NotBlank
  private String name;

}