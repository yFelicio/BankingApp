package com.revature.Util;

import java.util.Iterator;
import java.util.List;

import com.revature.Bank.Account;
import com.revature.Bank.Employee;

public class EmployeeUtility {
	
	
	public static Employee attemptLogIn(String username, String password) {
		// must search Employee Lists
		List<Employee> customers = SerializeUtility.getEmployees();
		
		Iterator<Employee> i = customers.iterator();
		
		while (i.hasNext()) {
			Employee current = i.next();
			if (current.getUsername().equals(username) && current.getPassword().equals(password)) {
				return current;
			}
		}
		
		return null;
	}
	
	public static double setBalance(Account account, double amount) {
		account.setBalance(amount);
		SerializeUtility.updateAccount(account);
		return account.getBalance();
	}
	
	public static String freezeAccount(Account account) {
		if (!account.isActive()) {
			return "Account is already frozen!";
		}
		account.setActive(false);
		if (SerializeUtility.updateAccount(account).equals("success")) {
			return "Account is now frozen";
		}
		return null;
	}
	public static String unFreezeAccount(Account account) {
		if (account.isActive()) {
			return "Account is already active!";
		}
		account.setActive(true);
		if (SerializeUtility.updateAccount(account).equals("success")) {
			return "Account is now Active";
		}
		return null;
	}
	
}
