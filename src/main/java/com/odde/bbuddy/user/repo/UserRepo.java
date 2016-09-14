package com.odde.bbuddy.user.repo;

import com.odde.bbuddy.user.domain.User;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepo extends Repository<User, Long> {

    void save(User user);
    void deleteAll();

}
