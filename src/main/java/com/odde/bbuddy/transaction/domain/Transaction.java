package com.odde.bbuddy.transaction.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.DAY;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Type type;

    @NotBlank
    private String description;

    @NotNull @Past @DateTimeFormat(pattern = DAY)
    private Date date;

    @NotNull @Min(1)
    private Integer amount;

    public enum Type {
        Income, Outcome
    }

}
