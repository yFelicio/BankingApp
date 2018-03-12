package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.Application;
import com.revature.pojo.Customer;

public interface CustomerDAO {

	
	public void signUp(String username, String password);
	public Customer getCustomer(String username, String password);
	public List<Customer> getAllCustomers();
	public Customer getCustomerFromUsername(String username);
	public List<Account> getAccounts(int customer_id);
	List<Application> getApplications(int customer_id);
	
}
