package com.odde.bbuddy.user.repo;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String userName;

    @NonNull
    private String password;

}
