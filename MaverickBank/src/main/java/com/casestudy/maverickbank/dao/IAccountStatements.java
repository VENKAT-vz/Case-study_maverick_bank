package com.casestudy.maverickbank.dao;

import java.sql.SQLException;

public interface IAccountStatements {
    public String generateAccountStatement(String accountNumber) throws SQLException, ClassNotFoundException;
    public String generateFinancialReport(String accountNumber) throws SQLException, ClassNotFoundException;

}
