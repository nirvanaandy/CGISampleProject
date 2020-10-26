package com.example.sampleproject.domain;

import org.springframework.hateoas.RepresentationModel;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * @author leima
 */
@Entity
public class Account extends RepresentationModel<Account> implements Serializable {

    private static final long serialVersionUID = 8283950216116626180L;

    @Id
    @Column(length = 9)
    @NotBlank(message = "Account Number should not be blank.")
    private String accountNumber;

    @Column(length = 20)
    @NotBlank(message = "Account Number should not be blank.")
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private AccountTypeEnum accountType;

    @Past(message = "Balance Date must be past time.")
    private Date balanceDate;

    @Column(length = 3)
    private Currency accountCurrency;

    @NotNull(message = "Available Balance should not be null.")
    private BigDecimal availableBalance;

    @NotNull(message = "Belonged User ID should not be null.")
    private Integer accountUserId;

    public Account() {
    }

    public Account(String accountNumber, String accountName, AccountTypeEnum accountType, Date balanceDate, Currency accountCurrency, BigDecimal availableBalance, Integer accountUserId) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balanceDate = balanceDate;
        this.accountCurrency = accountCurrency;
        this.availableBalance = availableBalance;
        this.accountUserId = accountUserId;
    }

    //Sure can use Bidirectional mapping. But only @ManytoOne in AccountTransaction entity already works, so comment @OneToMany here
    //@OneToMany(mappedBy = "belongedAccount", cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
    //private List<AccountTransaction> accountTransactions = new ArrayList<>();

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

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public Date getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    public Currency getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(Currency accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Integer getAccountUserId() {
        return accountUserId;
    }

    public void setAccountUserId(Integer accountUserId) {
        this.accountUserId = accountUserId;
    }

    @Override
    public String toString(){
        return this.accountUserId+" "+this.accountNumber+" "+this.accountName+" "+this.accountType+" "+ this.accountCurrency.toString()
                + this.balanceDate.toString()+" "+this.availableBalance;
    }
}


