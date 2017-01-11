package com.odde.bbuddy.transaction.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface TransactionRepo extends Repository<Transaction, Long> {
    void save(Transaction transaction);

    Page<Transaction> findAll(Pageable pageable);
}
