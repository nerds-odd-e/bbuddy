package com.odde.bbuddy.acceptancetest.data.account;

import com.odde.bbuddy.account.repo.Account;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AccountRepoForTest extends Repository<Account, Long> {
    List<Account> findAll();

    void deleteAll();

    void save(Account account);
}
