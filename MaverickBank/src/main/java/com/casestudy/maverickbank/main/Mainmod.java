package com.casestudy.maverickbank.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.casestudy.maverickbank.dao.ValidateUserImpl;
import com.casestudy.maverickbank.model.UserDetails;

public class Mainmod {

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		
		ValidateUserImpl validate=new ValidateUserImpl();
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to the banking system");
		System.out.println("Do you have an user profile? If not /n Press 1. for Registration else Press 2. for login");
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1: 
				System.out.println("Enter your first name: ");
				String fname=sc.next();
				System.out.println("Enter your last name: ");
				String lname=sc.next();
				System.out.println("Enter your gender(MALE/FEMALE): ");
				String gender=sc.next();
				System.out.println("Enter your contact number: ");
				String contactnumber=sc.next();

				System.out.println("Enter your address: ");
				String address=sc.next();

				System.out.println("Enter your city: ");
				String city=sc.next();

				System.out.println("Enter your state: ");
				String state=sc.next();

				System.out.println("Enter your username: ");
				String username=sc.next();

				System.out.println("Enter your password: ");
				String password=sc.next();
				UserDetails user=new UserDetails(fname,lname,gender,contactnumber,address,city,state,username,password);
				System.out.println(validate.registeruser(user)); 
				
				break;
				
		case 2:
			System.out.println("Enter the username");
			String uname=sc.next();
			System.out.println("Enter the password");
			String pass=sc.next();
			System.out.println(validate.loginuser(uname, pass)); 
			break;
		}
	}
}
