package com.example.sampleproject.dao;

import com.example.sampleproject.domain.AccountTransViewInfo;
import com.example.sampleproject.domain.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {

    //Use JPQL select a new View(Account, AccountTransaction)
    @Query(value="select new com.example.sampleproject.domain.AccountTransViewInfo(a.accountNumber, a.accountName, a.accountCurrency, "+
            " t.debitAmount, t.creditAmount, t.transactionType, t.valueDate, t.transactionNarrative, a.accountUserId) from Account a, AccountTransaction t" +
            " where a.accountNumber = ?1 and t.belongedAccount.accountNumber = a.accountNumber order by t.valueDate")
    Page<AccountTransViewInfo> findTransactionByAccountNumberUsingView(String accountNumber, Pageable pageable);

    //Use native SQL
    @Query(nativeQuery = true, value = "select a.account_number accountNumber, a.account_name accountName, t.value_date valuedDate, "+
            " a.account_currency accountCurrency, t.debit_amount debitAmount, t.credit_amount creditAmount, t.transaction_type transactionType, "+
            " t.transaction_narrative transactionNarrative, a.account_user_id accountUserId from account a, account_transaction t "+
            " where a.account_number = ?1 and a.account_number = t.belonged_account", countQuery = "select count(*) from account_transaction t "+
            " where t.belonged_account = ?1")
    public Page<Map> findTransactionsByAccountNumber(String accountNumber, Pageable pageable);
}
