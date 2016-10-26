package com.odde.bbuddy.account.domain;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int balanceBroughtForward;

}
