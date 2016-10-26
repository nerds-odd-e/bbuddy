package com.odde.bbuddy.account.repo;

import com.odde.bbuddy.account.domain.Account;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface AccountRepo extends Repository<Account, Long> {
    void save(Account account);
}
