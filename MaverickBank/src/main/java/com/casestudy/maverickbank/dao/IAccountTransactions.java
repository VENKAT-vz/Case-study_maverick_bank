package com.casestudy.maverickbank.dao;

import com.casestudy.maverickbank.model.Accounts;

public interface IAccountTransactions {
    void deposit(Accounts account, double amount);
    void withdraw(Accounts account, double amount);
    void transfer(Accounts fromAccount, Accounts toAccount, double amount);
}
