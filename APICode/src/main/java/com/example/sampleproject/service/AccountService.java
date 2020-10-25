package com.example.sampleproject.service;

import com.example.sampleproject.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    public List<Account> findAll();

    public Account createAccount(Account account);

    public boolean deleteAccount(Account account);

    public Account findAccountByNumber(String accountNumber);

    public Page<Account> findAccoutsByUserId(int userId, Pageable pageable);
}
