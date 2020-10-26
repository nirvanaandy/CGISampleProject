package com.example.sampleproject.serviceImpl;

import com.example.sampleproject.dao.AccountTransactionRepository;
import com.example.sampleproject.domain.AccountTransViewInfo;
import com.example.sampleproject.domain.AccountTransaction;
import com.example.sampleproject.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Autowired
    AccountTransactionRepository transactionRepository;

    @Override
    public boolean createTransaction(AccountTransaction accountTransaction) {

        transactionRepository.save(accountTransaction);
        return true;
    }

    @Override
    public List<AccountTransaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Page<Map> findTransactionsByAccountNumber(String accountNumber, Pageable pageable) {
        Page<Map> results = transactionRepository.findTransactionsByAccountNumber(accountNumber, pageable);

        if( results.getTotalElements() == 0 )
            return null;

        return results;
    }

    @Override
    @Cacheable(value = "findTransactionByAccountNumberUsingViewCache", key = "#accountNumber + #pageable.offset + #pageable.pageSize")
    public Page<AccountTransViewInfo> findTransactionByAccountNumberUsingView(String accountNumber, Pageable pageable) {

        Page<AccountTransViewInfo> results = transactionRepository.findTransactionByAccountNumberUsingView(accountNumber, pageable);

        if(results.getTotalElements() == 0)
            return null;

        return results;
    }
}
