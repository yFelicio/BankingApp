package main;

import com.revature.Bank.Admin;
import com.revature.Bank.Employee;
import com.revature.Util.EmployeeUtility;
import com.revature.Util.SerializeUtility;
import com.revature.Util.UIUtility;

public class Main {

	public static void main(String[] args) {
		/*
		 * Project is already initiliazed with an admin and employee user
		 * Those are:
		 * username: admin 			password: pass
		 * username: employee1 		password: pass
		 * 
		 */
		UIUtility ui = new UIUtility();
		ui.viewMainMenu();

	}

}
