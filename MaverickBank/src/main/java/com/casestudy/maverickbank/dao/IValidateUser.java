package com.casestudy.maverickbank.dao;

import java.sql.SQLException;

import com.casestudy.maverickbank.model.UserDetails;

public interface IValidateUser {

	String registeruser(UserDetails user) throws ClassNotFoundException, SQLException;
	String loginuser(String username,String password) throws ClassNotFoundException, SQLException;
}
