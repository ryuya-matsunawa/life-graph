package com.lifegraph.team20.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {

  private int id;

  private String username;

  private LocalDateTime updated_at;

}