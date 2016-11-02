package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.common.validator.Unique;
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

    @Unique(fieldCheck = Accounts.class)
    private String name;

    private int balanceBroughtForward;

}
