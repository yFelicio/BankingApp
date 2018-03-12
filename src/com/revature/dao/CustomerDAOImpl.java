package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Util.ConnectionFactory;
import com.revature.Util.LoggingUtil;
import com.revature.pojo.Account;
import com.revature.pojo.Application;
import com.revature.pojo.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	PreparedStatement stmt = null;
	
	@Override
	public void signUp(String username, String password) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO USERS (USERNAME, PASSWORD, TYPE_ID_FK) VALUES(?,?,1)";	
		try {
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
		
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Customer getCustomer(String username, String password) {
		Customer customer = null;
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM USERS WHERE TYPE_ID_FK = 1 AND USERNAME = ? AND PASSWORD = ?";
		
		try {
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				customer = new Customer();
				customer.setCustomerID(rs.getInt("USER_ID"));
				customer.setUsername(rs.getString("USERNAME"));
				customer.setPassword(rs.getString("PASSWORD"));
				
				// get accounts & applications associated with this customer
				if (getAccounts(customer.getCustomerID()) != null) {
					customer.setAccounts(getAccounts(customer.getCustomerID()));
				}
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM USERS WHERE TYPE_ID_FK = 1";
		
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getInt("user_id"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setAccounts(getAccounts(customer.getCustomerID()));
				customers.add(customer);
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return customers;
	}

	@Override
	public List<Account> getAccounts(int customer_id) {
		List<Account> accounts = new ArrayList<>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT ACCOUNTS.* FROM ACCOUNTS JOIN USERS_ACCOUNTS "
				+ "ON ACCOUNTS.ACCOUNT_ID = USERS_ACCOUNTS.ACCOUNT_ID "
				+ "WHERE USERS_ACCOUNTS.USER_ID = ?";
		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer_id);
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
	public List<Application> getApplications(int customer_id) {
		List<Application> applications = new ArrayList<>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM APPLICATIONS WHERE USER_ID_FK = ?";
		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer_id);
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				Application application = new Application();
				application.setApplicationID(rs.getInt("APPLICATION_ID"));
				application.setStatus(rs.getString("STATUS"));
				applications.add(application);
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return applications;
	}

	@Override
	public Customer getCustomerFromUsername(String username) {
		Customer customer = null;
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM USERS WHERE TYPE_ID_FK = 1 AND USERNAME = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				customer = new Customer();
				customer.setCustomerID(rs.getInt("USER_ID"));
				customer.setUsername(rs.getString("USERNAME"));
				LoggingUtil.logDebug("customerid: "+customer.getCustomerID());
				
				// get accounts & applications associated with this customer
				if (getAccounts(customer.getCustomerID()) != null) {
					customer.setAccounts(getAccounts(customer.getCustomerID()));
				}
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return customer;

	}

}
