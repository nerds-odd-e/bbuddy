package com.odde.bbuddy.account.repo;

import com.odde.bbuddy.account.domain.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AccountRepo extends Repository<Account, Long> {
    List<Account> findAll();
    void save(Account account);

    @Query("SELECT CASE WHEN COUNT(account) > 0 THEN 'true' ELSE 'false' END from Account account where account.name = ?1")
    boolean existsByName(String name);
}
