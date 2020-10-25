package com.example.sampleproject.serviceImpl;

import com.example.sampleproject.dao.AccountRepository;
import com.example.sampleproject.domain.Account;
import com.example.sampleproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @CachePut(value="findAccountByNumberCache", key = "#account.accountNumber")
    public Account createAccount(Account account) {
        Account result = accountRepository.save(account);
        return result;
    }

    @Override
    @CacheEvict(value = "findAccountByNumberCache", key = "#account.accountNumber")
    public boolean deleteAccount(Account account) {

        accountRepository.delete(account);
        return true;
    }

    @Override
    @Cacheable(value="findAccountByNumberCache", key="#accountNumber")
    public Account findAccountByNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if(account.isPresent()) {
            return (Account)account.get();
        } else {
            return null;
        }
    }

    @Override
    //@Cacheable(value="findAccountsByUserIdCache", key="#userId + #pageable.offset + #pageable.pageSize")
    public Page<Account> findAccoutsByUserId(int userId, Pageable pageable) {

        Page<Account> results = accountRepository.findAccountByUserId(userId, pageable);

        if( results.getTotalElements() == 0 )
            return null;

        return results;
    }


}
