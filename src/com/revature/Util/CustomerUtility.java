package com.revature.Util;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.Bank.Account;
import com.revature.Bank.Application;
import com.revature.Bank.Customer;
import com.revature.Exceptions.UserAlreadyExistsException;

public class CustomerUtility {

	public CustomerUtility() {
		super();
	}
	
	public static Customer attemptLogIn(String username, String password) {
		// must search customer and Admin Lists
		List<Customer> customers = SerializeUtility.getCustomers();
		
		Iterator<Customer> i = customers.iterator();
		
		while (i.hasNext()) {
			Customer current = i.next();
			if (current.getUsername().equals(username) && current.getPassword().equals(password)) {
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
				//System.out.println("Sorry that username is already taken");
				throw new UserAlreadyExistsException();
			}
		}
		
		Customer newCustomer = new Customer(username, password);
		
		if (SerializeUtility.addCustomer(newCustomer).equals("success")) {
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
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean createAccount(Customer customer) {	
		Logger log = Logger.getRootLogger();
		if (customer.getAccountID() != null) {
			return false;
		}
		
		Account account = new Account();

		log.debug("account id = " + account.getAccountID().toString());
		customer.setAccountID(account.getAccountID());

		log.debug("customer account id = " + customer.getAccountID().toString());
		if (SerializeUtility.addAccount(account).equals("success") 
				&& SerializeUtility.updateCustomer(customer).equals("success")) {
			log.debug("account created");
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
		Logger log = Logger.getRootLogger();
		log.debug("balance = " + account.getBalance());
		log.debug("deposit amount = " + amount);
		account.deposit(amount);	
		log.debug("new balance = " + account.getBalance());
	
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
		Account account = getAccountFromCustomer(customer);
		
		if (amount >= account.getBalance()) {
			return "You don't have that much money in the account";
		}
		account.withdraw(amount);	
		if (SerializeUtility.updateAccount(account).equals("success")) {
			return ("Your new balance is: "+ account.getBalance());
		} else {
			return "Deposit Failed";
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
		// get account from current customer
		Account account = getAccountFromCustomer(customer);
		
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
			return username+" Added to Account";
		} else {
			return "Could not add to Account";
		}
		
	}
	
	
	
}