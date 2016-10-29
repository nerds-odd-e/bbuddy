package com.odde.bbuddy.account.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int balanceBroughtForward;

}
