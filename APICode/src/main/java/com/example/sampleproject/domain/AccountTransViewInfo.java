package com.example.sampleproject.domain;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class AccountTransViewInfo extends RepresentationModel<AccountTransViewInfo> {

    private String accountNumber;

    private String accountName;

    private Currency accountCurrency;

    public AccountTransViewInfo(String accountNumber, String accountName, Currency accountCurrency,
                                BigDecimal debitAmount, BigDecimal creditAmount, TransactionTypeEnum transactionType,
                                Date valueDate, String transactionNarrative, Integer accountUserId) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountCurrency = accountCurrency;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.transactionType = transactionType;
        this.valueDate = valueDate;
        this.transactionNarrative = transactionNarrative;
        this.accountUserId = accountUserId;
    }

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private TransactionTypeEnum transactionType;

    private Date valueDate;

    private String transactionNarrative;

    private Integer accountUserId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Currency getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(Currency accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getTransactionNarrative() {
        return transactionNarrative;
    }

    public void setTransactionNarrative(String transactionNarrative) {
        this.transactionNarrative = transactionNarrative;
    }

    public Integer getAccountUserId() {
        return accountUserId;
    }

    public void setAccountUserId(Integer accountUserId) {
        this.accountUserId = accountUserId;
    }

    @Override
    public String toString(){
        return this.accountNumber+" "+this.accountName+" "+this.accountCurrency+" "+this.creditAmount +
                this.debitAmount+" "+this.transactionType+" "+this.valueDate+" "+this.transactionNarrative + this.accountUserId;
    }
}
