package com.odde.bbuddy.user.domain;

import com.odde.bbuddy.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInitializer {

    @Autowired
    UserRepo userRepo;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        userRepo.deleteAll();
        userRepo.save(new User("user", "password"));
    }
}
