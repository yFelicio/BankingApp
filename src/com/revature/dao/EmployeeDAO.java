package com.revature.dao;

import com.revature.pojo.Employee;

public interface EmployeeDAO {

	public Employee logIn(String username, String password);
	
}
