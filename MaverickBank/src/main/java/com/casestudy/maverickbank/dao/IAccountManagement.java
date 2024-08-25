package com.casestudy.maverickbank.dao;

import com.casestudy.maverickbank.model.Accounts;

public interface IAccountManagement {
	Accounts createAccount(String accountType, double initialDeposit, String branchName, String ifscCode);
    void closeAccount(Accounts account);

}
