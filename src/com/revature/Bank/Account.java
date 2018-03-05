package com.revature.Bank;

import java.io.Serializable;
import java.util.UUID;


public class Account implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1527367150779674053L;
	private final UUID accountID;
	private double balance;
	private boolean active;
	
	public Account() {
		accountID = UUID.randomUUID();
		balance = 0;
		setActive(true);
	}
	
	public Account(double balance) {
		accountID = UUID.randomUUID();
		this.balance = balance;
		setActive(true);
	}
	
	public UUID getAccountID() {
		return accountID;
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
