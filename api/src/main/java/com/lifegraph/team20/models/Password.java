package com.lifegraph.team20.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Password {

  private int userId;

  private String currentPassword;

  private String newPassword;
}
