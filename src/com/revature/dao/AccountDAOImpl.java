package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Util.ConnectionFactory;
import com.revature.Util.LoggingUtil;
import com.revature.pojo.Account;

public class AccountDAOImpl implements AccountDAO {

	PreparedStatement stmt = null;
	
	@Override
	public void createAccount(int customer_id) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		// get the next value of the account id
		String sql = "SELECT MAX(ACCOUNT_ID) FROM ACCOUNTS";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			int account_id = 0;
			while (rs.next()) {
				account_id = rs.getInt(1);
			}
			account_id++;
			rs.close();
			// insert into accounts & user_accounts
			sql = "INSERT INTO ACCOUNTS  (BALANCE, ACTIVE) VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, 0.0);
			stmt.setInt(2, 1);
			stmt.executeUpdate();
			
			
			sql = "INSERT INTO USERS_ACCOUNTS VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer_id);
			stmt.setInt(2, account_id);
			stmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		String sql = "SELECT * FROM ACCOUNTS";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setAccountID(rs.getInt("ACCOUNT_ID"));
				account.setBalance(rs.getDouble("BALANCE"));
				account.setActive(rs.getBoolean("ACTIVE"));
				accounts.add(account);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public void updateAccount(Account account) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		LoggingUtil.logDebug("account id: " + account.getAccountID() +" balance: " + account.getBalance());
		String sql = "UPDATE ACCOUNTS SET BALANCE = ?, ACTIVE = ? WHERE ACCOUNT_ID = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, account.getBalance());
			stmt.setBoolean(2, account.isActive());
			stmt.setInt(3, account.getAccountID());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Account getAccount(int account_id) {
		Account account = null;
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		String sql = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, account_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				account = new Account();
				account.setAccountID(rs.getInt("ACCOUNT_ID"));
				account.setBalance(rs.getDouble("BALANCE"));
				account.setActive(rs.getBoolean("ACTIVE"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;

	}

	@Override
	public void addUserToAccount(int customer_id, int account_id) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO USERS_ACCOUNTS VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer_id);
			stmt.setInt(2, account_id);
			stmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void transfer(int account_from, int account_to, double amount) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			CallableStatement cStmt = conn.prepareCall("{call TRANSFER_PROC(?, ?, ?)}");
			cStmt.setInt(1, account_from);
			cStmt.setInt(2, account_to);
			cStmt.setDouble(3, amount);
			cStmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
