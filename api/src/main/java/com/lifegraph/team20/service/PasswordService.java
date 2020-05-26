package com.lifegraph.team20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.models.Password;
import com.lifegraph.team20.repository.PasswordRepository;

@Service
public class PasswordService {

  @Autowired
  private PasswordRepository passwordRepository;

  public void passChange(Password user) {

    //パスワードとIDで正しいユーザか判定
    if (passwordRepository.existByIdPassword(user)) {
      // パスワード変更
      passwordRepository.changePassword(user);
    } else {
      return;
    }
  }
}
