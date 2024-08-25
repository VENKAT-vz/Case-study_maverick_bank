package com.casestudy.maverickbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.casestudy.maverickbank.model.UserDetails;
import com.casestudy.maverickbank.util.ConnectionUtil;
import com.mysql.cj.protocol.Resultset;

public class ValidateUserImpl implements IValidateUser{
	
	Connection connection;
	PreparedStatement preparedStatement;

	@Override
	public String registeruser(UserDetails user) throws ClassNotFoundException, SQLException {
		String query="INSERT INTO users(firstname,lastname,gender,contact_number,address,city,state,username,password) VALUES(?,?,?,?,?,?,?,?,?)";
		connection=ConnectionUtil.getConnection();
		preparedStatement=connection.prepareStatement(query);
		
		preparedStatement.setString(1,user.getFirstname());
		preparedStatement.setString(2,user.getLastname());
		preparedStatement.setString(3,user.getGender());
		preparedStatement.setString(4,user.getContactNumber());
		preparedStatement.setString(5,user.getAddress());
		preparedStatement.setString(6,user.getCity());
		preparedStatement.setString(7,user.getState());
		preparedStatement.setString(8,user.getUsername());
		preparedStatement.setString(9,user.getPassword());

		preparedStatement.executeUpdate();
		
		return "Account Created...";
		
	}

	@Override
	public String loginuser(String username, String password) throws ClassNotFoundException, SQLException {
		 String query = "SELECT * FROM users WHERE username = ? AND password = ?";

	        connection=ConnectionUtil.getConnection();
	         preparedStatement = connection.prepareStatement(query);
	         
	         preparedStatement.setString(1, username);
	         preparedStatement.setString(2, password);
	            
	          ResultSet rs = preparedStatement.executeQuery();
	                if (rs.next()) {
	                    return "Login successful";
	                }
	          return "Invalid Credentials. Try again.";
	            
	}

}
