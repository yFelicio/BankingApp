package com.revature.Bank;

import java.io.Serializable;
import java.util.List;

public class Admin extends Employee implements Serializable {

	
	
	
	public Admin(String username, String password) {
		super(username, password);
	}
	
	// this overriden method will be able to view all customer information and their account information
	@Override
	public List<Application> viewCustomers() {
		// TODO Auto-generated method stub
		return super.viewCustomers();
	}

}
