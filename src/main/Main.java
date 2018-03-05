package main;

import com.revature.Bank.Admin;
import com.revature.Bank.Employee;
import com.revature.Exceptions.UserAlreadyExistsException;
import com.revature.Util.CustomerUtility;
import com.revature.Util.EmployeeUtility;
import com.revature.Util.SerializeUtility;
import com.revature.Util.UIUtility;

public class Main {

	public static void main(String[] args) {
		/*
		 * Project is initiliazed with an admin and employee, and no customers
		 * Those are:
		 * username: admin 			password: pass	(admin)
		 * username: employee1 		password: pass	(employee)
		 * 
		 * This next block of code will initialize those accounts
		 */
		Employee admin = new Admin("admin","pass");
		Employee emp = new Employee("employee1", "pass");
		if (!SerializeUtility.getEmployees().contains(admin)) {
			SerializeUtility.addEmployee(admin);
		}
		if (!SerializeUtility.getEmployees().contains(admin)) {
			SerializeUtility.addEmployee(emp);
		}
		
		// this code brings up the User Interface
		UIUtility ui = new UIUtility();
		ui.viewMainMenu();

	}

}
