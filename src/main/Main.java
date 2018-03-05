package main;

import com.revature.Bank.Admin;
import com.revature.Bank.Employee;
import com.revature.Util.EmployeeUtility;
import com.revature.Util.SerializeUtility;
import com.revature.Util.UIUtility;

public class Main {

	public static void main(String[] args) {
		/*
		 * Project is already initiliazed with an admin and employee, and two customer users
		 * Those are:
		 * username: admin 			password: pass	(admin)
		 * username: employee1 		password: pass	(employee)
		 * username: yuri 			password: pass	(customer)
		 * username: customer1		password: pass	(customer)
		 * 
		 */
		UIUtility ui = new UIUtility();
		ui.viewMainMenu();

	}

}
