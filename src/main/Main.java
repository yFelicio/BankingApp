package main;

import com.revature.Util.CustomerUtility;
import com.revature.Util.EmployeeUtility;
import com.revature.Util.UIUtility;
import com.revature.pojo.Admin;
import com.revature.pojo.Employee;

public class Main {

	public static void main(String[] args) {
		/*
		 * Project is initilized with an admin and employee, and no customers
		 * Those are:
		 * username: admin 			password: pass	(admin)
		 * username: employee1 		password: pass	(employee)
		 * 
		 * This next block of code will initialize those accounts
		 */
		
		// this code brings up the User Interface
		UIUtility ui = new UIUtility();
		ui.viewMainMenu();

	}

}
