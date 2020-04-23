package com.qnq.jmsjta.repository;

import java.util.UUID;

import com.qnq.jmsjta.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

  UserEntity findByUsername(String username);
}
