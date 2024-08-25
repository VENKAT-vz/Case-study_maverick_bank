package com.casestudy.maverickbank.model;

import java.sql.Date;

public class Transactions {

	private int transid;
	private int accNo;
	private double transAmount;
	private Date transDate;
	private String transType;
	
	public Transactions(int transid, int accNo, double transAmount, Date transDate, String transType) {
		this.accNo = accNo;
		this.transAmount = transAmount;
		this.transDate = transDate;
		this.transType = transType;
		this.transid=transid;
	}
	
	public Transactions() {

	}
	
	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public int getTransid() {
		return transid;
	}

	public void setTransid(int transid) {
		this.transid = transid;
	}

	@Override
	public String toString() {
		return "Transactions [accNo=" + accNo + ", transid=" + transid + ", transAmount=" + transAmount + ", transDate="
				+ transDate + ", transType=" + transType + "]";
	}

}

