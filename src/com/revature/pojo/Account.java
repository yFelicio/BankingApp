package com.revature.pojo;

import java.io.Serializable;
import java.util.UUID;


public class Account {

	
	private int accountID;
	private double balance;
	private boolean active;
	
	public Account() {
		balance = 0;
		setActive(true);
	}
	
	public Account(double balance) {
		this.balance = balance;
		setActive(true);
	}
	
	public Account(int accountID, double balance, boolean active) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.active = active;
	}

	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double amount) {
		balance = amount;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void withdraw(double amount) {
		balance -= amount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
