package com.qnq.jmsjta.service;

import static com.qnq.jmsjta.config.ActiveMQConfig.MESSAGE_QUEUE;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.qnq.jmsjta.entity.UserEntity;
import com.qnq.jmsjta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final JmsTemplate jmsTemplate;
  private final UserRepository userRepository;

  @Override
  public String createUser(String username) {
    log.debug("saveUser: ", username);
    jmsTemplate.convertAndSend(MESSAGE_QUEUE, "JMS received User : " + username);
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(username);
    return userRepository.save(userEntity).getUserId().toString();
  }

  @Override
  public Stream<UserEntity> queryUser() {
    return StreamSupport.stream(userRepository.findAll().spliterator(), false);
  }
}
