package com.odde.bbuddy.transaction.repo;

import com.odde.bbuddy.common.validator.Past;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.odde.bbuddy.common.Formats.DAY;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private Type type;

    @NotBlank
    private String description;

    @NotNull
    @DateTimeFormat(pattern = DAY)
    @Past
    private LocalDate date;

    @NotNull
    @Min(1)
    private Integer amount;

    public enum Type {
        Income, Outcome
    }

}
