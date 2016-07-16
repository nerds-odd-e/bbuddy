package com.odde.bbuddy.user;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepo extends CrudRepository<User, Long> {
}
