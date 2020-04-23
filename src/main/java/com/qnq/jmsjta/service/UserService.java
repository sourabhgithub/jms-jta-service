package com.qnq.jmsjta.service;

import com.qnq.jmsjta.entity.UserEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface UserService {
  String createUser(String username);

  Stream<UserEntity> queryUser();
}
