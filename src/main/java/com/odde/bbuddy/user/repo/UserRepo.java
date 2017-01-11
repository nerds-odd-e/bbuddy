package com.odde.bbuddy.user.repo;

import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepo extends Repository<User, Long> {

    void save(User user);

    void deleteAll();

}
