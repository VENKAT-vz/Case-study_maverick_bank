package com.casestudy.maverickbank.model;

public class Loans {
    private String loanId;
    private String accountId;
    private String loanType;
    private double loanAmount;
    private String status;  
    private int installments;

    public Loans(String loanId, String accountId, String loanType, double loanAmount, String status, int installments) {
        this.loanId = loanId;
        this.accountId = accountId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.status = status;
        this.installments = installments;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

	@Override
	public String toString() {
		return "Loans [loanId=" + loanId + ", accountId=" + accountId + ", loanType=" + loanType + ", loanAmount="
				+ loanAmount + ", status=" + status + ", installments=" + installments + "]";
	}
}
