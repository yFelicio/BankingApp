package com.revature.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.Bank.Account;
import com.revature.Bank.Customer;
import com.revature.Bank.Employee;
import com.revature.Exceptions.UserAlreadyExistsException;
import com.revature.Util.CustomerUtility;
import com.revature.Util.EmployeeUtility;
import com.revature.Util.SerializeUtility;

public class tests {
	static Customer customer = new Customer("yuri", "pass");
	static Account account = new Account();
	/*
	 * Running tests will RESET all saved data!
	 * This method will reset all files and initialize a customer with an account
	 */
	@BeforeClass
	public static void doYourOneBuild() {
		new File("accounts.dat").delete();
	    new File("applications.dat").delete();
	    new File("customers.dat").delete();
	    new File("employees.dat").delete();
	       
		customer.setAccountID(account.getAccountID());
		account.deposit(500);
		SerializeUtility.addCustomer(customer);
		SerializeUtility.addAccount(account);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*
	 * Test that user cannot deposit a negative amount
	 */
	@Test
	public void testDepositNegativeAmount() {
		assertEquals("Deposit must be greater than 0", CustomerUtility.makeDeposit(customer, -50));	
	}
	
	/*
	 * Test that user can deposit a positive amount
	 */
	@Test
	public void testDepositPositiveAmount() {
		String result = CustomerUtility.makeDeposit(customer, 50);	
		if (result.contains("Your new balance is:")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	/* 
	 * Test that user cannot withdraw a negative number
	 * Should return a predefined string
	 */
	@Test
	public void testWithdrawNegativeAmount() {
		assertEquals("You cannot withdraw a negative Amount", CustomerUtility.makeWithdrawal(customer, -50));
	}
	/*
	 * Test customer method to withdraw an amount
	 * customer needs more than $5 in account
	 */
	@Test
	public void testWithdrawPositiveAmount() {
		String result = CustomerUtility.makeWithdrawal(customer, 5);	
		if (result.contains("Your new balance is:")) {
			assert(true);
		} else {
			assert(false);
		}
	}
	
	/*
	 * Test withdrawing a huge amount
	 */
	public void testWithdrawHugeAmount() {
		String result = CustomerUtility.makeWithdrawal(customer, 5000000.0);
		assertEquals("You don't have that much money in the account", result);
	}
	
	/*
	 * Test to sign up user
	 * First parameter in method must be unique!
	 */
	@Test 
	public void testCustomerSignUp() throws UserAlreadyExistsException {
		// generate random username
		String custName = UUID.randomUUID().toString();
		custName = custName.substring(0, 10);
				
		assertTrue(CustomerUtility.attemptSignUp(custName, "pass"));
	}
	
	/*
	 * test that the login method returns a Customer object
	 */
	@Test
	public void testCustomerSignIn() {
		if (CustomerUtility.attemptLogIn("yuri", "pass") instanceof Customer) {
			assert(true);
		} else {
			assert(false);
		}
	}
	
	/*
	 * See if method throws user already exists exception
	 */
	@Test
	public void testCustomerSignUpWithExistingUser() throws UserAlreadyExistsException {
		expectedException.expect(UserAlreadyExistsException.class);
		CustomerUtility.attemptSignUp("yuri", "pass");
	}
	
	/*
	 * Test if admin's set balance works
	 */
	@Test
	public void testEmployeeChangeBalance()  {
		assertEquals("200.0", ""+EmployeeUtility.setBalance(account, 200.0));
		
	}
	
	/*
	 * Test admin freeze ability
	 * result should say the account is already frozen, or is now frozen
	 */
	@Test
	public void testAdminFreeze() {
		String result = EmployeeUtility.freezeAccount(account);
		if (result.equals("Account is already frozen") || result.equals("Account is now frozen")) {
			assert(true);
		} else {
			assert(false);
		}
	}
	
	/*
	 * Test admin unfreeze method
	 * Result should say account is already active, or is now active
	 */
	@Test
	public void testAdminUnFreeze() {
		String result = EmployeeUtility.unFreezeAccount(account);
		if (result.equals("Account is already active!") || result.equals("Account is now Active")) {
			assert(true);
		} else {
			assert(false);
		}
	}
	/*
	 * Test get account from username
	 * account id of account and customer objects should match
	 */
	@Test
	public void testGetAccountFromCustomer() {
		Account acc = CustomerUtility.getAccountFromCustomer(customer);
		assertTrue(acc.getAccountID().equals(customer.getAccountID()));
	}
	
	/*
	 * test that a new customer object, does not have an account
	 * Result should be a null account object
	 */
	@Test
	public void testGetAccountFromNonExistentCustomer() {

		// generate random username
		String custName = UUID.randomUUID().toString();
		custName = custName.substring(0, 10);
		
		Customer newC = new Customer(custName, "pass");
		Account acc = CustomerUtility.getAccountFromCustomer(newC);
		assertNull(acc);
	}
	
	/* 
	 * test that and employee object can successfully sign up
	 */
	@Test
	public void testAdminSignUp() {
		// generate random username
		String empName = UUID.randomUUID().toString();
		empName = empName.substring(0, 10);
		
		Employee emp1 = new Employee(empName,"pass");
		assertEquals("success", SerializeUtility.addEmployee(emp1));
	}
	
	/*
	 * 
	 */
	
	
	
	/*
	 * delete all files
	 */
	@AfterClass
    public static void doYourOneTimeTeardown() {
       new File("accounts.dat").delete();
       new File("applications.dat").delete();
       new File("customers.dat").delete();
       new File("employees.dat").delete();
       
    }  
	
	
}
