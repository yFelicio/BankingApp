package com.revature.Bank;

import java.io.Serializable;
import java.util.UUID;

import com.revature.Exceptions.NegativeBalanceException;
import com.revature.Exceptions.NotEnoughFundsException;

public class Account implements Serializable {

	
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
