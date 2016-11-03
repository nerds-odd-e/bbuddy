package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.common.validator.Unique;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    @NotBlank @Unique(fieldCheck = Accounts.class)
    private String name;

    @NotNull @Min(0)
    private Integer balanceBroughtForward;

}
