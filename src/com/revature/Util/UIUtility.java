package com.revature.Util;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.Bank.Account;
import com.revature.Bank.Admin;
import com.revature.Bank.Application;
import com.revature.Bank.Customer;
import com.revature.Bank.Employee;
import com.revature.Exceptions.UserAlreadyExistsException;

public class UIUtility {

	/* This is the UI Utility class
	 * Here is where all the menus will be displayed, and all the user input will be handled
	 * */

	
	
	public UIUtility() {
		
	}

	/* Menu that is created when the application is started
	 * 1. Log in
	 * 2. Sign Up
	 * 3. Employee Area
	 * 4. Exit
	 */
	public void viewMainMenu() {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("******MAIN MENU******");
		System.out.println("1. Log In");
		System.out.println("2. Sign Up");
		System.out.println("3. Employee Area");
		System.out.println("4. Exit");
		System.out.print("Select a menu item (enter number): ");
		String choice = scan.nextLine();
		
		if (choice.equals("1")) {
			logInMenu();
		} else if (choice.equals("2")) {
			signUpMenu();
		}  else if (choice.equals("3")) {
			employeeLogIn();
		} else if (choice.equals("4")) {
			exit();
		} else {
			System.out.println("Invalid Option try again.");
			viewMainMenu();
		}
		
	}
	
	/*
	 * This menu will prompt the user to log in
	 * It will ask for the username, and then the password
	 * It will then pass this to the customer utility, which will attempt a login.
	 * If successful user will be taken to the customer menu.
	 * If login fails then the user will be taken back to the main menu
	 */
	public void logInMenu() {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("******Log In******");
		
		System.out.print("Enter Username: ");
		String username = scan.nextLine();
		
		System.out.print("Enter Password: ");
		String password = scan.nextLine();
		
		Customer customer = CustomerUtility.attemptLogIn(username, password);
		if (customer != null) {
			System.out.println("Signed In Successfully");
			customerMenu(customer);
		} else {
			System.out.println("Username or Password are incorrect.");
			viewMainMenu();
		}
	}
	/*
	 * Prompts user to sign up by entering a username, password, and re-entering the password
	 * Then the CustomerUtility will attempt to sign up the user. 
	 */
	public void signUpMenu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("******Sign Up******");
		
		System.out.print("Choose a Username: ");
		String username = scan.nextLine();
		
		System.out.print("Choose a Password: ");
		String password = scan.nextLine();

		System.out.print("Confirm Password: ");
		if (!(password.equals(scan.next()))) {
			System.out.println("passwords do not match! Enter again");
			signUpMenu();
		}
		boolean success = false;
		try {
			success = CustomerUtility.attemptSignUp(username, password);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Username already exists, pick another.");
			signUpMenu();
		} finally {
			if (success) {
				System.out.println("Signed Up Successful");
			} else {
				System.out.println("Sign Up Failed");
			}
		}
		viewMainMenu();	
	}
	
	public void employeeLogIn() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("******Employee Log In******");
		
		System.out.print("Enter Username: ");
		String username = scan.nextLine();
		
		System.out.print("Enter Password: ");
		String password = scan.nextLine();
		
		Employee employee = EmployeeUtility.attemptLogIn(username, password);
		if (employee != null) {
			System.out.println("Signed In Successfully");
			employeeMenu(employee);
		} else {
			System.out.println("Username or Password are incorrect.");
			viewMainMenu();
		}
	}
	
	/* 
	 * This menu will have the customers options.
	 * 1. View Account
	 * 2. Create Account
	 * 3. View Application
	 * 4. Sign out
	 */
	public void customerMenu(Customer customer) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("******Customer Menu******");
		System.out.println("1. View Account");
		System.out.println("2. Create Account");
		System.out.println("3. View Application");
		System.out.println("4. Add another user to your Account");
		System.out.println("5. Sign Out");
		System.out.print("Select a menu item (enter number): ");
		String choice = scan.nextLine();
		
		if (choice.equals("1")) {
			accountMenu(customer);
		} else if (choice.equals("2")) {
			createAccount(customer);
		} else if (choice.equals("3")) {
			viewApplication(customer);
		} else if (choice.equals("4")) {
			addUserToAccount(customer);
		} else if (choice.equals("5")) {
			viewMainMenu();
		} else {
			System.out.println("Invalid Choice");
			customerMenu(customer);
		}
		
	}
	
	/*
	 * This section will simply ask you to confirm whether or not you would like to create an account
	 * It will also check if you already have an account, and return you to the customer menu.
	 */
	public void createAccount(Customer customer) {
		if (customer.getApplication() != null) {
			System.out.println("You have already applied for an account!");
			customerMenu(customer);
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("*******Create Accunt******");
		System.out.println("Would you like to apply for an account y/n?");
		String confirm = scan.nextLine();
		if (confirm.equals("y")) {
			if (CustomerUtility.applyForAccount(customer)) {
				System.out.println("Account Application Created, approval pending");
				customerMenu(customer);
			}
		} else {
			customerMenu(customer);
		}
	}
	
	/*
	 * This will print the status of the application
	 * Once the user enters anything they will be returned to the customer menu
	 */
	public void viewApplication(Customer customer) {
		Scanner scan = new Scanner(System.in);
		Application application = customer.getApplication();
		if (application != null) {
			String status = application.getStatus();
			System.out.println("\nApplication status: " + status);
		} else {
			System.out.println("\nApplication status: You have not tried to create an account");
		}
		System.out.print("Hit enter to go back ");
		String back = scan.nextLine();
		customerMenu(customer);
		
	}
	
	/*
	 * Where a user can add another user to their account, by entering in their username
	 *  
	 */
	public void addUserToAccount(Customer customer) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a user to add to your account: ");
		String username = scan.nextLine();
		
		System.out.println(CustomerUtility.addUserToAccount(customer, username));
		customerMenu(customer);
	}
	/*
	 * Shows account balance, and gives option to withdraw or deposit
	 */
	public void accountMenu(Customer customer) {	

		Scanner scan = new Scanner(System.in);
		if (customer.getAccountID() != null) {
			Account account = CustomerUtility.getAccountFromCustomer(customer);
			Logger log = Logger.getRootLogger();
			log.debug("ui account : " + account);
			if (account == null) {
				log.error("account is null");
				customerMenu(customer);
			}
			if (!account.isActive()) {
				System.out.println("Your Account is Frozen");
				log.debug(customer.getUsername() + " tried to access a frozen account");
				customerMenu(customer);
			}
			double balance = account.getBalance();
			log.debug("ui balance : "+balance);
			System.out.println("******Account Menu*******");
			System.out.println("Balance: " + balance);
			System.out.println("1. Make a Deposit");
			System.out.println("2. Make a Withdraw");
			System.out.println("3. Go Back");
			System.out.print("Select a menu item (enter number): ");
			String choice = scan.nextLine();
			if (choice.equals("1")) {
				makeDeposit(customer);
			} else if (choice.equals("2")) {
				makeWithdrawal(customer);
			} else if (choice.equals("3")) {
				customerMenu(customer);
			}
		} else {
			System.out.println("You don't have an account yet!");
			System.out.print("Hit enter to go back ");
			String back = scan.nextLine();
			customerMenu(customer);
		}
		
	}
	
	public void makeDeposit(Customer customer) {
		Scanner scan = new Scanner(System.in);
		System.out.print("How much would you like to deposit? ");
		String line = scan.nextLine();
		double amount = Double.parseDouble(line);
		// make deposit and return new account balance
		System.out.println(CustomerUtility.makeDeposit(customer, amount));
		accountMenu(customer);
	}
	
	public void makeWithdrawal(Customer customer) {
		Scanner scan = new Scanner(System.in);
		System.out.print("How much would you like to withdraw? ");
		String line = scan.nextLine();
		double amount = Double.parseDouble(line);
		System.out.println(CustomerUtility.makeWithdrawal(customer, amount));
		accountMenu(customer);
	}
	
	/*
	 * Creates the menu for the employee
	 * 1. View All Customers
	 * 2. View All Accounts
	 * 3. View All Applications
	 * 4. Sign out
	 */
	public void employeeMenu(Employee employee) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("******Employee Menu*******");
		System.out.println("1. View All Customers");
		System.out.println("2. View All Accounts");
		System.out.println("3. View All Applications");
		System.out.println("4. Sign Out");
		System.out.print("Select a menu item (enter number): ");
		String choice = scan.nextLine();
		
		if (choice.equals("1")) {
			viewCustomersInfo(employee);
		} else if (choice.equals("2")) {
			viewAccountsInfo(employee);
		} else if (choice.equals("3")) {
			 viewApplications(employee);
		} else if (choice.equals("4")) {
			viewMainMenu();
		} else {
			System.out.println("Invalid Choice");
			employeeMenu(employee);
		} 
	}
	
	/*
	 * Display customer information and their account information
	 */
	public void viewCustomersInfo(Employee employee) {

		//get list of customers
		List<Customer> customers = SerializeUtility.getCustomers();
		
		System.out.println("******Customers Info*******");
		System.out.println("* Username \t Account ID \t Balance \t Application Status");

		// Iterate through customers list and display information
		Iterator<Customer> i = customers.iterator();
		while (i.hasNext()) {
			Customer customer = i.next();
			String username = customer.getUsername(); 
			// account from current customer
			Account account = CustomerUtility.getAccountFromCustomer(customer);
			String accountID = account != null ? (account.getAccountID().toString()) : ("N/A");
			double balance = account != null ? (account.getBalance()) : (0);
			// application from current customer
			Application application = customer.getApplication();
			String status = application != null ? (application.getStatus()) : ("N/A");
			
			System.out.println("* " + username + " \t " 
					+ accountID + " \t "
					+ balance + " \t "
					+ status);
		}
		System.out.println("*******************************");

		Scanner scan = new Scanner(System.in);
		System.out.println("Hit enter to go back ");
		String enter = scan.nextLine();
		employeeMenu(employee);
	}
	
	public void viewAccountsInfo(Employee employee) {
		
		System.out.println("******Account Info*******");
		System.out.println("* Username \t Account ID \t Balance \t Status*");
		//get list of customers
		List<Customer> customers = SerializeUtility.getCustomers();	

		// Iterate through customers list and display information
		Iterator<Customer> i = customers.iterator();
		while (i.hasNext()) {
			Customer customer = i.next();
			String username = customer.getUsername(); 
			// account from current customer
			Account account = CustomerUtility.getAccountFromCustomer(customer);
			String accountID = account != null ? (account.getAccountID().toString()) : ("N/A");
			double balance = account != null ? (account.getBalance()) : (0);
			String status = account.isActive() ? ("Active") : ("Frozen");
 			System.out.println("* " + username + " \t " 
					+ accountID + " \t "
					+ balance + " \t "
					+ status);
		}
		System.out.println("*******************************");

		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a username to edit balance (or type exit to leave): ");
		String username = scan.nextLine();
		if (!(username.equals("exit"))) {
			if (employee instanceof Admin) {
				editAccountsInfo(employee, username);
			} else {
				System.out.println("You do not have admin privilages!");
				employeeMenu(employee);
			}
		} else {
			System.out.println("Could not find that username");
			employeeMenu(employee);
		}
	}
	
	public void editAccountsInfo(Employee employee, String username) {
		Account account = CustomerUtility.getAccountFromUsername(username);
		if (account != null) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter the new balance (or type 'freeze' or 'activate'): ");
			String line = scan.nextLine();
			if (line.equals("freeze")) {
				System.out.print("Are you sure you want to freeze this account y/n? ");
				String yn = scan.nextLine();
				if (yn.equals("y")) {
					System.out.println(EmployeeUtility.freezeAccount(account));
					LoggingUtil.logInfo(account + " has been frozen");
					viewAccountsInfo(employee);
				}
			} else if (line.equals("activate")) {
				System.out.print("Are you sure you want to activate this account y/n? ");
				String yn = scan.nextLine();
				if (yn.equals("y")) {
					System.out.println(EmployeeUtility.unFreezeAccount(account));
					LoggingUtil.logInfo(account + " has been activated");
					viewAccountsInfo(employee);
				}
			}
			double amount = Double.parseDouble(line);
			
			// ask are you sure?
			System.out.print("Are you sure y/n? ");
			String yn = scan.nextLine();
			if (yn.equals("y")) {
				account.setBalance(amount);
				SerializeUtility.updateAccount(account);
				// log new balance
				LoggingUtil.logInfo(username + " Account balance is now: " + account.getBalance());
				viewAccountsInfo(employee);
			} else {
				viewAccountsInfo(employee);
			}
		} else {
			System.out.println("Could not find an account for this user.");
			viewAccountsInfo(employee);
		}
	}
	
	/*
	 * Displays customers username and the status of their application, 
	 * or N/A if they don't have any application.
	 * Also allows the employee to select an application marked 'pending' and approve them.
	 */
	public void viewApplications(Employee employee) {
		// get list of Applications
		List<Customer> customers = SerializeUtility.getCustomers();
		
		System.out.println("******Application Info*******");
		System.out.println("* Username \t Status *");
		// Iterate through customers list and display information
		Iterator<Customer> i = customers.iterator();
		while (i.hasNext()) {
			Customer customer = i.next();
			Application application = customer.getApplication();
			String username = customer.getUsername();
			String status = application != null ? (application.getStatus()) : ("N/A");
			System.out.println("* "+ username +" \t " + status);
		}
		System.out.println("*******************************");

		Scanner scan = new Scanner(System.in);
		System.out.print("Would you like to approve some applications y/n? ");
		String enter = scan.nextLine();
		if (enter.equals("y")) {
			approveApplications(employee, customers);
		} else {
			employeeMenu(employee);
		}
	}
	
	public void approveApplications(Employee employee, List<Customer> customers) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a customers username to approve: ");
		String username = scan.nextLine();
		Iterator<Customer> i = customers.iterator();
		while (i.hasNext()) {
			Customer customer = i.next();
			if (customer.getUsername().equals(username)) {
				customer.getApplication().setStatus("approved");
				CustomerUtility.createAccount(customer);
			}
		}
		viewApplications(employee);
	}
	
	
	public void exit() {
		System.exit(0);
	}
	
}
