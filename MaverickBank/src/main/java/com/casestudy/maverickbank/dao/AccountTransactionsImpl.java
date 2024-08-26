package com.casestudy.maverickbank.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.casestudy.maverickbank.util.ConnectionUtil;

public class AccountTransactionsImpl implements IAccountTransactions {

    @Override
    public void deposit(String accountnumber, double amount) throws ClassNotFoundException, SQLException {
        String updateAccountQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        String insertTransactionQuery = "INSERT INTO transactions (account_number, trans_amount, trans_date, trans_type) VALUES (?, ?, ?, 'deposit')";

        Connection connection = ConnectionUtil.getConnection();
             PreparedStatement updateAccountStmt = connection.prepareStatement(updateAccountQuery);
             PreparedStatement insertTransactionStmt = connection.prepareStatement(insertTransactionQuery); 

            updateAccountStmt.setDouble(1, amount);
            updateAccountStmt.setString(2, accountnumber);
            updateAccountStmt.executeUpdate();

            insertTransactionStmt.setString(1, accountnumber);
            insertTransactionStmt.setDouble(2, amount);
            insertTransactionStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            insertTransactionStmt.executeUpdate();
        } 
        

    @Override
    public void withdraw(String accountnumber, double amount) throws ClassNotFoundException, SQLException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        String updateAccountQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String insertTransactionQuery = "INSERT INTO transactions (account_number, trans_amount, trans_date, trans_type) VALUES (?, ?, ?, 'withdrawal')";

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement updateAccountStmt = connection.prepareStatement(updateAccountQuery);
        PreparedStatement insertTransactionStmt = connection.prepareStatement(insertTransactionQuery);

            double currentBalance = getCurrentBalance(accountnumber, connection);
            if (currentBalance < amount) {
                throw new IllegalArgumentException("Insufficient funds");
            }

            updateAccountStmt.setDouble(1, amount);
            updateAccountStmt.setString(2, accountnumber);
            updateAccountStmt.executeUpdate();

            insertTransactionStmt.setString(1, accountnumber);
            insertTransactionStmt.setDouble(2, amount);
            insertTransactionStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            insertTransactionStmt.executeUpdate();

        } 
    

    @Override
    public void transfer(String accountnumber, String accountnumber1, double amount) throws ClassNotFoundException, SQLException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        String updateAccountQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String updateToAccountQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        String insertFromTransactionQuery = "INSERT INTO transactions (account_number, trans_amount, trans_date, trans_type) VALUES (?, ?, ?, 'transfer-out')";
        String insertToTransactionQuery = "INSERT INTO transactions (account_number, trans_amount, trans_date, trans_type) VALUES (?, ?, ?, 'transfer-in')";

        Connection connection = ConnectionUtil.getConnection();
          PreparedStatement updateFromAccountStmt = connection.prepareStatement(updateAccountQuery);
             PreparedStatement updateToAccountStmt = connection.prepareStatement(updateToAccountQuery);
          PreparedStatement insertFromTransactionStmt = connection.prepareStatement(insertFromTransactionQuery);
        PreparedStatement insertToTransactionStmt = connection.prepareStatement(insertToTransactionQuery);

            double currentBalance = getCurrentBalance(accountnumber, connection);
            if (currentBalance < amount) {
                throw new IllegalArgumentException("Insufficient funds for transfer");
            }

            updateFromAccountStmt.setDouble(1, amount);
            updateFromAccountStmt.setString(2, accountnumber);
            updateFromAccountStmt.executeUpdate();

            updateToAccountStmt.setDouble(1, amount);
            updateToAccountStmt.setString(2, accountnumber1);
            updateToAccountStmt.executeUpdate();

            insertFromTransactionStmt.setString(1, accountnumber);
            insertFromTransactionStmt.setDouble(2, amount);
            insertFromTransactionStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            insertFromTransactionStmt.executeUpdate();

            insertToTransactionStmt.setString(1, accountnumber1);
            insertToTransactionStmt.setDouble(2, amount);
            insertToTransactionStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            insertToTransactionStmt.executeUpdate();

        }

    private double getCurrentBalance(String accountNumber, Connection connection) throws SQLException {
        String query = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("balance");
                }
                throw new SQLException("Account not found: " + accountNumber);
            }
      }
    	}
}

