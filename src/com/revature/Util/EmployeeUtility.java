package com.revature.Util;

import java.util.Iterator;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ApplicationDAO;
import com.revature.dao.ApplicationDAOImpl;
import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDAOImpl;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.pojo.Account;
import com.revature.pojo.Application;
import com.revature.pojo.Customer;
import com.revature.pojo.Employee;

public class EmployeeUtility {
	
	
	public static Employee attemptLogIn(String username, String password) {
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		
		Employee employee = employeeDAO.logIn(username, password);
		return employee;
	}
	
	
	public static boolean setBalance(Account account, double amount) {
		AccountDAO accountDAO = new AccountDAOImpl();
		
		account.setBalance(amount);
		accountDAO.updateAccount(account);
		return true;
	}
	
	public static List<Customer> getAllCustomers() {
		CustomerDAO customerDAO = new CustomerDAOImpl();
		
		List<Customer> customers = customerDAO.getAllCustomers();
		return customers;
	}
	
	public static List<Application> getAllApplications() {
		ApplicationDAO applicationDAO = new ApplicationDAOImpl();
		List<Application> applications = applicationDAO.getAllApplications();
		return applications;
	}
	public static boolean updateApplication(Application application) {
		ApplicationDAO applicationDAO = new ApplicationDAOImpl();
		AccountDAO accountDAO = new AccountDAOImpl();
		
		application.setStatus("approved");
		LoggingUtil.logInfo("Application id: " + application.getApplicationID() + " has been approved");
		applicationDAO.updateApplication(application);
		accountDAO.createAccount(application.getUserID());
		return true;
	}
	public static String freezeAccount(Account account) {
		if (!account.isActive()) {
			return "Account is already frozen!";
		}
		
		account.setActive(false);
		AccountDAO accountDAO = new AccountDAOImpl();
		accountDAO.updateAccount(account);
		return "Account is now Frozen";
	}
	public static String unFreezeAccount(Account account) {
		if (account.isActive()) {
			return "Account is already active!";
		}
		account.setActive(true);
		AccountDAO accountDAO = new AccountDAOImpl();
		accountDAO.updateAccount(account);
		return "Account is now Active";
	}
	
	public static Account getAccountFromID(int account_id) {
		AccountDAO accountDAO = new AccountDAOImpl();
		Account account = accountDAO.getAccount(account_id);
		return account;
	}
}
