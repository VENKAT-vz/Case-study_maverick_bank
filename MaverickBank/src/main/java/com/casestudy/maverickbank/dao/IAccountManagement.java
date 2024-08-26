package com.casestudy.maverickbank.dao;

import java.sql.SQLException;
import java.util.List;

import com.casestudy.maverickbank.model.Accounts;

public interface IAccountManagement {


	String createAccount(String account_type, double balance, String username)
			throws SQLException, ClassNotFoundException;


	String closeAccount(String accountnumber, String username) throws ClassNotFoundException, SQLException;
	
	public List<Accounts> searchAccounts(String username) throws SQLException, ClassNotFoundException;


}
