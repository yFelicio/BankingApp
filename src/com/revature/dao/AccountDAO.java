package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;

public interface AccountDAO {

	public void createAccount(int customer_id);
	public List<Account> getAllAccounts();
	public void updateAccount(Account account);
	public Account getAccount(int account_id);
	public void addUserToAccount(int customer_id, int account_id);
	public void transfer(int account_from, int account_to, double amount);
	
}
