package com.casestudy.maverickbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.casestudy.maverickbank.model.Accounts;
import com.casestudy.maverickbank.util.ConnectionUtil;

public class AccountManagementImpl implements IAccountManagement{

	Connection connection;
	PreparedStatement preparedStatement;
	
	@Override
	public String createAccount(String account_type,double balance,String username) throws SQLException, ClassNotFoundException {
		String accno = generateAccountNo();
		boolean usrchk=checkuser(username);
		Connection connection=ConnectionUtil.getConnection();
		Accounts account;
		if(usrchk) {
			account=new Accounts(accno,account_type,balance);
	        String query = "INSERT INTO accounts (account_number, account_type, balance, branch_name, ifsc_code, status, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, account.getAccountNumber());
			preparedStatement.setString(2, account.getAccountType());
			preparedStatement.setDouble(3, account.getBalance());
			preparedStatement.setString(4, account.getBranchName());
			preparedStatement.setString(5, account.getIfscCode());
			preparedStatement.setString(6, account.getStatus());
			preparedStatement.setString(7, username);

			preparedStatement.executeUpdate();
			return "Account Created with Account No " +accno;
		}
		else
			return "user is not registered. Register and create account";
	}
		
	@Override
	public String closeAccount(String accountNumber, String username) throws ClassNotFoundException, SQLException {
	    List<Accounts> accountList = searchAccounts(username);
	    
	    Optional<Accounts> accountToClose = accountList.stream()
	        .filter(account -> account.getAccountNumber().equals(accountNumber))
	        .findFirst();
	    
	    if (accountToClose.isPresent()) {
	        Accounts account = accountToClose.get();
	        if ("active".equals(account.getStatus())) {
	            account.setStatus("ToBeClosed");

	            try (Connection connection = ConnectionUtil.getConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(
	                     "UPDATE accounts SET status = ? WHERE account_number = ? AND username = ?")) {

	                preparedStatement.setString(1, account.getStatus());
	                preparedStatement.setString(2, account.getAccountNumber());
	                preparedStatement.setString(3, username);

	                preparedStatement.executeUpdate();
	                return "Account will be closed by the manager in coming days.";
	            } catch (SQLException | ClassNotFoundException e) {
	                e.printStackTrace();
	                return "Error occurred while processing the request.";
	            }
	        } else {
	            return "Account is not active and cannot be closed.";
	        }
	    } else {
	        return "Account not found or user is not authorized.";
	    }
	}

	
	public boolean checkuser(String username) throws ClassNotFoundException, SQLException {
		String query = "SELECT username FROM users where username=?";
		connection = ConnectionUtil.getConnection();
		preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next())
			return true;
		return false;
		
	}
	
	public List<Accounts> searchAccounts( String username) throws SQLException, ClassNotFoundException {
	    List<Accounts> accountsList = new ArrayList<>();
	    String query = "SELECT * FROM accounts WHERE username = ?";
	    
	    Connection connection = ConnectionUtil.getConnection();
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	         
	        preparedStatement.setString(1, username);
	        
	        ResultSet rs = preparedStatement.executeQuery() ;
	        
	            while (rs.next()) {
	                String accNo = rs.getString("account_number");
	                String accType = rs.getString("account_type");
	                double balance = rs.getDouble("balance");
	                String branchName = rs.getString("branch_name");
	                String ifscCode = rs.getString("ifsc_code");
	                String status = rs.getString("status");
	                
	                Accounts account = new Accounts(accNo, accType, balance);
	                account.setBranchName(branchName);
	                account.setIfscCode(ifscCode);
	                account.setStatus(status);
	                
	                accountsList.add(account);
	            }

	    
	    return accountsList;
	}

	
	public String generateAccountNo() throws ClassNotFoundException, SQLException {
		String query = "select case when max(account_number) IS NULL THEN 1000 else max(account_number)+1 "
				+ "end accno from accounts";
		connection = ConnectionUtil.getConnection();
		preparedStatement = connection.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		int accno = rs.getInt("accno");		
		return Integer.toString(accno); 
	}

}
