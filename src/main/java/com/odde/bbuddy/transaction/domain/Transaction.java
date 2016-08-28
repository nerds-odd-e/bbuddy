package com.odde.bbuddy.transaction.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    private Type type;
    private String description;

    @DateTimeFormat(pattern = DAY)
    private Date date;
    private Integer amount;

    public enum Type {
        Income
    }

}
