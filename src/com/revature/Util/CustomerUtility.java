package com.revature.Util;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.Bank.Account;
import com.revature.Bank.Application;
import com.revature.Bank.Customer;
import com.revature.Exceptions.UserAlreadyExistsException;

public class CustomerUtility {

	
	
	public static Customer attemptLogIn(String username, String password) {
		// must search customer and Admin Lists
		List<Customer> customers = SerializeUtility.getCustomers();
		
		Iterator<Customer> i = customers.iterator();
		
		while (i.hasNext()) {
			Customer current = i.next();
			if (current.getUsername().equals(username) && current.getPassword().equals(password)) {
				LoggingUtil.logInfo(username + " has just logged in");
				return current;
			}
		}
		
		return null;
		
	}
	
	public static boolean attemptSignUp(String username, String password) throws UserAlreadyExistsException {
		
		//First check if username is already taken
		List<Customer> customers = SerializeUtility.getCustomers();
		Iterator<Customer> i = customers.iterator();
		while (i.hasNext()) {
			if (i.next().getUsername().equals(username)) {
				throw new UserAlreadyExistsException();
			}
		}
		
		Customer newCustomer = new Customer(username, password);
		
		if (SerializeUtility.addCustomer(newCustomer).equals("success")) {
			LoggingUtil.logInfo(newCustomer.getUsername() + " has created an account");
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean applyForAccount(Customer customer) {
		
		if (customer.getApplication() != null) {
			return false;
		}
		
		Application application = new Application();
		
		customer.setApplication(application);
		// add application to file
		if (SerializeUtility.addApplication(application).equals("success") 
				&& SerializeUtility.updateCustomer(customer).equals("success")) {
			LoggingUtil.logInfo(customer.getUsername() + " has applied for an account");
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean createAccount(Customer customer) {	
		if (customer.getAccountID() != null) {
			return false;
		}
		
		Account account = new Account();

		LoggingUtil.logInfo("account id = " + account.getAccountID().toString());
		customer.setAccountID(account.getAccountID());

		LoggingUtil.logInfo("customer account id = " + customer.getAccountID().toString());
		if (SerializeUtility.addAccount(account).equals("success") 
				&& SerializeUtility.updateCustomer(customer).equals("success")) {
			LoggingUtil.logInfo("account created");
			return true;
		} else {
			return false;
		}	
	}
	
	public static String makeDeposit(Customer customer, double amount) {
		if (customer.getAccountID() == null) {
			return "You don't have an account";
		}
		if (amount <= 0) {
			return "Deposit must be greater than 0";
		}
		Account account = getAccountFromCustomer(customer);
		LoggingUtil.logInfo("balance = " + account.getBalance());
		LoggingUtil.logInfo("deposit amount = " + amount);
		account.deposit(amount);	
		LoggingUtil.logInfo("new balance = " + account.getBalance());
	
		if (SerializeUtility.updateAccount(account).equals("success")) {
			return ("Your new balance is: "+account.getBalance());
		} else {
			return "Deposit Failed";
		}
		
	}
	public static String makeWithdrawal(Customer customer, double amount) {
		if (customer.getAccountID() == null) {
			return "You don't have an account";
		}
		if (amount < 0) {
			return "You cannot withdraw a negative Amount";
		}
		Account account = getAccountFromCustomer(customer);

		LoggingUtil.logInfo("balance = " + account.getBalance());
		LoggingUtil.logInfo("Withdraw amount = " + amount);
		
		if (amount >= account.getBalance()) {
			return "You don't have that much money in the account";
		}
		account.withdraw(amount);	
		if (SerializeUtility.updateAccount(account).equals("success")) {
			LoggingUtil.logInfo("new balance = " + account.getBalance());
			return ("Your new balance is: "+ account.getBalance());
		} else {
			LoggingUtil.logError("Withdraw failed");
			return "Withdraw Failed";
		}
		
	}
	
	public static Account getAccountFromCustomer(Customer customer) {
		
		List<Account> accounts = SerializeUtility.getAccounts();
		Iterator<Account> i = accounts.iterator();
		while(i.hasNext()) {
			Account account = i.next();
			if ((account.getAccountID()).equals((customer.getAccountID()))) {
				return account;
			}
		}
		return null;
	}
	
	public static Account getAccountFromUsername(String username) {
		List<Customer> customers = SerializeUtility.getCustomers();
		Iterator<Customer> i = customers.iterator();
		
		while (i.hasNext()) {
			Customer customer = i.next();
			if (customer.getUsername().equals(username)) {
				return getAccountFromCustomer(customer);
			}
		}
		return null;
	}
	
	public static Customer getCustomerFromUsername(String username) {
		List<Customer> customers = SerializeUtility.getCustomers();
		Iterator<Customer> i = customers.iterator();
		
		while (i.hasNext()) {
			Customer customer = i.next();
			if (customer.getUsername().equals(username)) {
				return customer;
			}
		}
		return null;
	}

	public static String addUserToAccount(Customer customer, String username) {
		
		// get new customer to be added from username
		Customer newUser = getCustomerFromUsername(username);
		if (newUser == null) {
			return "User not Found";
		}
		if (newUser.getAccountID() != null) {
			return "This user already has an Account!";
		}
		newUser.setAccountID(customer.getAccountID());
		if (SerializeUtility.updateCustomer(newUser).equals("success")) {
			LoggingUtil.logInfo(customer.getUsername() + " has added " + username + " to their account");
			return username+" Added to Account";
		} else {
			LoggingUtil.logError("Could not add to account");
			return "Could not add to Account";
		}
		
	}
	
	
	
}
