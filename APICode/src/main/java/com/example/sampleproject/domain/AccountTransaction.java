package com.example.sampleproject.domain;

import org.springframework.hateoas.RepresentationModel;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class AccountTransaction extends RepresentationModel<AccountTransaction> implements Serializable {

    private static final long serialVersionUID = 1227555837798655046L;

    @Id
    @GeneratedValue
    private long id;

    private Date valueDate;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionType;

    @Column(length = 50)
    private String transactionNarrative;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "belonged_account")
    private Account belongedAccount;

    public AccountTransaction() {

    }

    public AccountTransaction(Date valueDate, BigDecimal debitAmount, BigDecimal creditAmount, TransactionTypeEnum transactionType, String transactionNarrative, Account belongedAccount) {
        this.valueDate = valueDate;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.transactionType = transactionType;
        this.transactionNarrative = transactionNarrative;
        this.belongedAccount = belongedAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
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

    public String getTransactionNarrative() {
        return transactionNarrative;
    }

    public void setTransactionNarrative(String transactionNarrative) {
        this.transactionNarrative = transactionNarrative;
    }

    public Account getBelongedAccount() {
        return belongedAccount;
    }

    public void setBelongedAccount(Account belongedAccount) {
        this.belongedAccount = belongedAccount;
    }

    @Override
    public String toString(){
        return this.belongedAccount.getAccountNumber() + " " + this.belongedAccount.getAccountName() + " " +
                this.valueDate + " " + this.belongedAccount.getAccountCurrency().toString() + " "+
                this.debitAmount + " " + this.creditAmount + " " + this.transactionType + " " +
                this.transactionNarrative;
    }
}
