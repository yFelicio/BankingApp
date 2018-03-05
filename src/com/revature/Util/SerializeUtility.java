package com.revature.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.revature.Bank.Account;
import com.revature.Bank.Application;
import com.revature.Bank.Customer;
import com.revature.Bank.Employee;

public class SerializeUtility {

	private static List<Application> applications = new LinkedList<>();
	private static List<Customer> customers = new LinkedList<>();
	private static List<Employee> employees = new LinkedList<>();
	
	
	public SerializeUtility() {
		// TODO Auto-generated constructor stub
	}


	public static List<Application> getApplications() {
		List<Application> savedApplications = null;
		if (new File("applications.dat").exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("applications.dat"))) {
				savedApplications = (List<Application>) ois.readObject();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			savedApplications = new LinkedList<>();
		}
		
		return savedApplications;
	}
	
	public static String addApplication(Application application) {
		//update list of applications
		applications = getApplications();
		// add customer to updated list of customers
		applications.add(application);
				
		// save in file
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("applications.dat"))) {
			oos.writeObject(applications);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Application failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Application failed";
		}
		
		
		return "success";
	}


	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}


	public static List<Account> getAccounts() {
		List<Account> savedAccounts = null;
		if (new File("accounts.dat").exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
				savedAccounts = (List<Account>) ois.readObject();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			savedAccounts = new LinkedList<>();
		}
		return savedAccounts;
		
	}

	public static String addAccount(Account account) {
		//update list of customers
		List<Account> accounts = getAccounts();
		// add customer to updated list of customers
		accounts.add(account);
				
		// save in file
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
			oos.writeObject(accounts);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Account creation failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Account creation failed";
		}
		return "success";
	}


	public static String updateAccount(Account account) {
		List<Account> accounts = getAccounts();
		Iterator<Account> i = accounts.iterator();
		
		while(i.hasNext()) {
			// if the account id match, then remove the old account object and replace with the new
			if (i.next().getAccountID().equals(account.getAccountID())) {
				i.remove();
				accounts.add(account);
				//write to file
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
					oos.writeObject(accounts);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "account update failed";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "account update failed";
				}
				
				return "success";
			}
		}
		
		return "failed";
	}


	public static List<Customer> getCustomers() {
		// read file and get all saved customers
		List<Customer> savedCustomers = null;
		if (new File("customers.dat").exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customers.dat"))) {
				savedCustomers = (List<Customer>) ois.readObject();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			savedCustomers = new LinkedList<>();
		}
		
		return savedCustomers;
	}

// not sure if needed, perhaps delete later
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public static String addCustomer(Customer customer) {
		//update list of customers
		customers = getCustomers();
		// add customer to updated list of customers
		customers.add(customer);
		
		// save in file
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.dat"))) {
			oos.writeObject(customers);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sign up failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sign up failed";
		}
		return "success";
	}
	
	public static String updateCustomer(Customer customer) {
		List<Customer> customers = getCustomers();
		Iterator<Customer> i = customers.iterator();
		
		while(i.hasNext()) {
			// if the usernames match, then remove the old customer object and replace with the new
			if (i.next().getUsername().equals(customer.getUsername())) {
				i.remove();
				customers.add(customer);
				//write to file
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.dat"))) {
					oos.writeObject(customers);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "sign up failed";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "sign up failed";
				}
				
				return "success";
			}
		}
		
		return "failed";
	}

	public static List<Employee> getEmployees() {
		// read file and get all saved customers
		List<Employee> savedEmployees = null;
		if (new File("employees.dat").exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employees.dat"))) {
				savedEmployees =  (List<Employee>) ois.readObject();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			savedEmployees = new LinkedList<>();
		}
		return savedEmployees;
	}

	public static String addEmployee(Employee employee) {
		//update list of customers
		employees = getEmployees();
		// add customer to updated list of customers
		employees.add(employee);
				
		// save in file
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employees.dat"))) {
			oos.writeObject(employees);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sign up failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sign up failed";
		}
		return "success";
	}

	public void setEmployees(List<Employee> employees) {
		
	}

	
	
	
}


