package com.casestudy.maverickbank.model;

public class Accounts {
    private String accountNumber;
    private String accountType;
    private double balance;
    private String branchName;
    private String ifscCode;
    private String Status;
    
    public Accounts() {
    	
    }
    
	public Accounts(String accountNumber, String accountType, double balance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.branchName = "Chennai";
		this.ifscCode = "GBI0003456";
		this.Status = "active";
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Accounts [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ ", branchName=" + branchName + ", ifscCode=" + ifscCode + ", Status=" + Status + "]";
	}
}
