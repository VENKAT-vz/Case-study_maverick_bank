package com.casestudy.maverickbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.casestudy.maverickbank.util.ConnectionUtil;

public class AccountStatementImpl implements IAccountStatements{

	Connection connection;
	PreparedStatement preparedstatement;
	@Override
	public String generateAccountStatement(String accountNumber) throws SQLException, ClassNotFoundException {
	     StringBuilder statement = new StringBuilder();
	        
	        String accountQuery = "SELECT * FROM accounts WHERE account_number = ?";
	         connection = ConnectionUtil.getConnection();
	         preparedstatement = connection.prepareStatement(accountQuery);
	         preparedstatement.setString(1, accountNumber);
	            ResultSet accountRs = preparedstatement.executeQuery();
	            
	            if (accountRs.next()) {
	                statement.append("Account Statement for Account Number: ").append(accountNumber).append("\n");
	                statement.append("Account Type: ").append(accountRs.getString("account_type")).append("\n");
	                statement.append("Balance: ").append(accountRs.getDouble("balance")).append("\n");
	                statement.append("Branch Name: ").append(accountRs.getString("branch_name")).append("\n");
	                statement.append("IFSC Code: ").append(accountRs.getString("ifsc_code")).append("\n");
	                statement.append("Status: ").append(accountRs.getString("status")).append("\n\n");
	            }
	        
	       
	        String transactionsQuery = "SELECT * FROM transactions WHERE account_number = ? ORDER BY trans_date DESC";
	        connection = ConnectionUtil.getConnection();
	        preparedstatement = connection.prepareStatement(transactionsQuery);
	        preparedstatement.setString(1, accountNumber);
	            ResultSet transactionsRs = preparedstatement.executeQuery();
	            
	            statement.append("Transaction History:\n");
	            while (transactionsRs.next()) {
	                statement.append("Date: ").append(transactionsRs.getDate("trans_date")).append(", ");
	                statement.append("Type: ").append(transactionsRs.getString("trans_type")).append(", ");
	                statement.append("Amount: ").append(transactionsRs.getDouble("trans_amount")).append("\n");
	            }
	   
	        
	        return statement.toString();
	    }	


	@Override
	public String generateFinancialReport(String accountNumber) throws SQLException, ClassNotFoundException {
        StringBuilder report = new StringBuilder();
        
        String depositsQuery = "SELECT SUM(trans_amount) FROM transactions WHERE account_number = ? AND trans_type = 'Deposit'";
         connection = ConnectionUtil.getConnection();
              preparedstatement = connection.prepareStatement(depositsQuery);
              preparedstatement.setString(1, accountNumber);
            ResultSet depositsRs = preparedstatement.executeQuery();
            
            if (depositsRs.next()) {
                report.append("Total Deposits: ").append(depositsRs.getDouble(1)).append("\n");
            }
        
        
        String withdrawalsQuery = "SELECT SUM(trans_amount) FROM transactions WHERE account_number = ? AND trans_type = 'Withdraw'";
        connection = ConnectionUtil.getConnection();
           preparedstatement = connection.prepareStatement(withdrawalsQuery);
           preparedstatement.setString(1, accountNumber);
            ResultSet withdrawalsRs = preparedstatement.executeQuery();
            
            if (withdrawalsRs.next()) {
                report.append("Total Withdrawals: ").append(withdrawalsRs.getDouble(1)).append("\n");
            }
        
        
        String balanceQuery = "SELECT balance FROM accounts WHERE account_number = ?";
        connection = ConnectionUtil.getConnection();
           PreparedStatement preparedstatement = connection.prepareStatement(balanceQuery);
          preparedstatement.setString(1, accountNumber);
            ResultSet balanceRs = preparedstatement.executeQuery();
            
            if (balanceRs.next()) {
                report.append("Current Balance: ").append(balanceRs.getDouble(1)).append("\n");
            }
        
        
        return report.toString();
    }	
}


