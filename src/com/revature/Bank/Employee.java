package com.revature.Bank;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable {

	
	private String username;
	private String password;
	
	
	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Application> viewApplications() {
		
		return null;
	}
	public void approveApplication(Application application) {
		application.setStatus("approved");
		application.createAccount();
	}
	public List<Application> viewCustomers() {
		return null;
		
	}
	
	
	
	
}
