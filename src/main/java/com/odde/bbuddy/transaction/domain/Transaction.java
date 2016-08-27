package com.odde.bbuddy.transaction.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Type type;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer amount;

    public enum Type {
        Income
    }

}
