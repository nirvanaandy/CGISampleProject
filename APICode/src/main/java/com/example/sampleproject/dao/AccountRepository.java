package com.example.sampleproject.dao;

import com.example.sampleproject.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

    @Query(value = "select a from Account a where a.accountUserId=?1")
    public Page<Account> findAccountByUserId(int userId, Pageable pageable);

    @Query(value = "select a from Account a where a.accountNumber=?1")
    public Account findAccountByAccountNumber(String accountNumber);
}
