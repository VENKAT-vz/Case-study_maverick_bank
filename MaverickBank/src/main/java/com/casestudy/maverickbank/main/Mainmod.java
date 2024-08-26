package com.casestudy.maverickbank.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.casestudy.maverickbank.dao.ValidateUserImpl;
import com.casestudy.maverickbank.model.Accounts;
import com.casestudy.maverickbank.model.UserDetails;
import com.casestudy.maverickbank.dao.IValidateUser;
import com.casestudy.maverickbank.dao.IAccountManagement;
import com.casestudy.maverickbank.dao.AccountManagementImpl;
import com.casestudy.maverickbank.dao.IAccountTransactions;
import com.casestudy.maverickbank.dao.AccountTransactionsImpl;
import com.casestudy.maverickbank.dao.IAccountStatements;
import com.casestudy.maverickbank.dao.AccountStatementImpl;

public class Mainmod {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        IValidateUser validate = new ValidateUserImpl();
        IAccountManagement manage = new AccountManagementImpl();
        IAccountTransactions trans = new AccountTransactionsImpl();
        IAccountStatements statements = new AccountStatementImpl();
        
        List<Accounts> accounts;
        Scanner sc = new Scanner(System.in);
        String loggedInUser = null;
        boolean loggedIn = false;

        System.out.println("Welcome to the banking system");
        
        while (true) {
            System.out.println("Do you have a user profile?");
            System.out.println("Type 1 for Registration");
            System.out.println("Type 2 for Login");
            System.out.println("Type 3 to Exit");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter your first name: ");
                    String fname = sc.next();
                    System.out.println("Enter your last name: ");
                    String lname = sc.next();
                    System.out.println("Enter your gender (MALE/FEMALE): ");
                    String gender = sc.next();
                    System.out.println("Enter your contact number: ");
                    String contactnumber = sc.next();
                    System.out.println("Enter your address: ");
                    String address = sc.next();
                    System.out.println("Enter your city: ");
                    String city = sc.next();
                    System.out.println("Enter your state: ");
                    String state = sc.next();
                    System.out.println("Enter your username: ");
                    String username = sc.next();
                    System.out.println("Enter your password: ");
                    String password = sc.next();
                    
                    UserDetails user = new UserDetails(fname, lname, gender, contactnumber, address, city, state, username, password);
                    System.out.println(validate.registeruser(user)); 
                    break;
                    
                case 2:
                    System.out.println("Enter your username: ");
                    String uname = sc.next();
                    System.out.println("Enter your password: ");
                    String pass = sc.next();
                    
                    if (validate.loginuser(uname, pass).equals("Login successful")) {
                        loggedIn = true;
                        loggedInUser = uname;
                        System.out.println("Login successful! You can now perform operations.");
                        
                        while (loggedIn) {
                            System.out.println("Operations Menu:");
                            System.out.println("Type 1 for creating an account");
                            System.out.println("Type 2 for applying for closing an account");
                            System.out.println("Type 3 for searching accounts");
                            System.out.println("Type 4 for Deposit");
                            System.out.println("Type 5 for Withdraw");
                            System.out.println("Type 6 for Transfer");
                            System.out.println("Type 7 for Account statements");
                            System.out.println("Type 8 to Financial reports");
                            System.out.println("Type 9 to Exit");

                            int operationChoice = sc.nextInt();
                            
                            switch (operationChoice) {
                                case 1:
                                    System.out.println("Enter the account type:");
                                    String acctype = sc.next();
                                    System.out.println("Enter the initial balance:");
                                    double bal = sc.nextDouble();
                                    
                                    System.out.println(manage.createAccount(acctype, bal, loggedInUser));
                                    break;
                                    
                                case 2:
                                    System.out.println("Enter the account number:");
                                    String accnum = sc.next();
                                    System.out.println(manage.closeAccount(accnum, loggedInUser)); 
                                    break;
                                    
                                case 3:
                                    System.out.println("Enter your username:");
                                    String user3 = sc.next();
                                    
                                    accounts = manage.searchAccounts(user3);
                                    accounts.forEach(x -> {
                                        System.out.println(x);
                                    });
                                    break;
                                
                                case 4:
                                    System.out.println("Enter the account number:");
                                    String accnum3 = sc.next();
                                    System.out.println("Enter the amount to be deposited:");
                                    double amount1 = sc.nextDouble();
                                    trans.deposit(accnum3, amount1);
                                    break;
                                    
                                case 5:
                                    System.out.println("Enter the account number:");
                                    String accnum4 = sc.next();
                                    System.out.println("Enter the amount to be withdrawn:");
                                    double amount2 = sc.nextDouble();
                                    trans.withdraw(accnum4, amount2);
                                    break;
        
                                case 6:
                                    System.out.println("Enter the from account number:");
                                    String accnum5 = sc.next();
                                    System.out.println("Enter the to account number:");
                                    String accnum6 = sc.next();
                                    System.out.println("Enter the amount to be transferred:");
                                    double amount3 = sc.nextDouble();
                                    trans.transfer(accnum5, accnum6, amount3);
                                    break;
                                    
                                case 7:
                                    System.out.println("Enter your account number:");
                                    String accnum7 = sc.next();
                                    System.out.println(statements.generateAccountStatement(accnum7));
                                    break;
                                case 8:
                                	System.out.println("Enter your account number:");
                                    String user4 = sc.next();
                                    System.out.println(statements.generateFinancialReport(user4));
                                    break;

                                case 9:
                                    sc.close();
                                    System.out.println("Exiting the system...");
                                    return;
                                    
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                    
                case 3:
                    sc.close();
                    System.out.println("Exiting the system...");
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
