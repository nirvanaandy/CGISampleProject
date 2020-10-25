package com.example.sampleproject.service;

import com.example.sampleproject.domain.AccountTransViewInfo;
import com.example.sampleproject.domain.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AccountTransactionService {

    public boolean createTransaction(AccountTransaction accountTransaction);

    public List<AccountTransaction> findAll();

    public Page<Map> findTransactionsByAccountNumber(String accountNumber, Pageable pageable);

    public Page<AccountTransViewInfo> findTransactionByAccountNumberUsingView(String accountNumber, Pageable pageable);
}
