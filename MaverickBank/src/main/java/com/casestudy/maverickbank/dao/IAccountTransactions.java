package com.casestudy.maverickbank.dao;

import java.sql.SQLException;

public interface IAccountTransactions {
    void deposit(String accountnumber, double amount) throws ClassNotFoundException, SQLException;
    void withdraw(String accountnumber, double amount) throws ClassNotFoundException, SQLException;
    void transfer(String accountnumber, String accountnumber1, double amount) throws ClassNotFoundException, SQLException;
}
