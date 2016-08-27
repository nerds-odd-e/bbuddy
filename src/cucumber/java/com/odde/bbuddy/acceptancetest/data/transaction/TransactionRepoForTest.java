package com.odde.bbuddy.acceptancetest.data.transaction;

import com.odde.bbuddy.transaction.domain.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Scope("cucumber-glue")
public interface TransactionRepoForTest extends Repository<Transaction, Long> {
    List<Transaction> findAll();
    void deleteAll();
}
