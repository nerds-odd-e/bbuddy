package com.odde.bbuddy.transaction.repo;

import com.odde.bbuddy.transaction.domain.Transaction;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TransactionRepo extends Repository<Transaction, Long> {
    void save(Transaction transaction);

    List<Transaction> findAll();

    void deleteAll();
}
