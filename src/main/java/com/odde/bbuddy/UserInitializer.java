package com.odde.bbuddy;

import com.odde.bbuddy.user.User;
import com.odde.bbuddy.user.UserRepo;
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
