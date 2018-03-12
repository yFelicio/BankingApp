package com.revature.Util;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ApplicationDAO;
import com.revature.dao.ApplicationDAOImpl;
import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDAOImpl;
import com.revature.pojo.Account;
import com.revature.pojo.Application;
import com.revature.pojo.Customer;

public class CustomerUtility {

	
	
	public static Customer attemptLogIn(String username, String password) {

		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		Customer customer = customerDAO.getCustomer(username, password);
		return customer;
		
	}
	
	public static String attemptSignUp(String username, String password) {
		// First check if username is already taken
		CustomerDAO customerDAO = new CustomerDAOImpl();
		List<Customer> customers = customerDAO.getAllCustomers();
		if (!(customers.isEmpty())) {
			Iterator<Customer> i = customers.iterator();	
			while (i.hasNext()) {
				Customer customer = i.next();
				if (customer.getUsername().equals(username)) {
					LoggingUtil.logInfo(username + " was already taken");
					return "Username already taken";
				}
			}
		}
		
		Customer newCustomer = new Customer(username, password);
		customerDAO.signUp(username, password);
		LoggingUtil.logInfo(newCustomer.getUsername() + " has created an account");
		return "Sucessfully Signed Up!";
	}
	
	
	public static boolean applyForAccount(Customer customer) {
		ApplicationDAO applicationDAO = new ApplicationDAOImpl();
		applicationDAO.createApplication(customer.getCustomerID());
		LoggingUtil.logInfo(customer.getUsername() + " has applied for an account");
		return true;
	}
	
	public static List<Application> getApplicationFromCustomer(Customer customer) {
		ApplicationDAO applicationDAO = new ApplicationDAOImpl();
		List<Application> applications = applicationDAO.getApplicationFromUser(customer.getCustomerID());
		return applications;
	}
	
	
	public static boolean createAccount(Customer customer) {	
		AccountDAO accountDAO = new AccountDAOImpl();
		accountDAO.createAccount(customer.getCustomerID());
		LoggingUtil.logInfo(customer.getUsername() + " has just created an account");
		return true;
	}
	
	public static String makeDeposit(Account account, double amount) {
		if (amount <= 0.0) {
			return "Deposit must be greater than 0.0";
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		
		LoggingUtil.logInfo("balance = " + account.getBalance());
		LoggingUtil.logInfo("deposit amount = " + amount);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		LoggingUtil.logInfo("new balance = " + account.getBalance());
		
		accountDAO.updateAccount(account);
		return ("Your new balance is: "+account.getBalance());
		
		
	}
	public static String makeWithdrawal(Account account, double amount) {
		if (amount < 0) {
			return "You cannot withdraw a negative Amount";
		}
		if (amount >= account.getBalance()) {
			return "You don't have that much money in the account";
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		
		LoggingUtil.logInfo("balance = " + account.getBalance());
		LoggingUtil.logInfo("Withdraw amount = " + amount);
		
		account.withdraw(amount);	
		accountDAO.updateAccount(account);
		LoggingUtil.logInfo("new balance = " + account.getBalance());
		return ("Your new balance is: "+ account.getBalance());
	}
	
	

	public static String addUserToAccount(int account_id, String username) {
		// get new customer to be added from username
		CustomerDAO customerDAO = new CustomerDAOImpl();
		Customer newUser = customerDAO.getCustomerFromUsername(username);
		if (newUser == null) {
			return "User not Found";
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		Account account = accountDAO.getAccount(account_id);
		if (account == null) {
			return "Account not Found";
		}
		accountDAO.addUserToAccount(newUser.getCustomerID(), account_id);
		LoggingUtil.logInfo(account_id + " has added " + newUser.getUsername() + " as an owner of the account");
		return username+" has been added to the account";
		
	}
	
	
	
}
