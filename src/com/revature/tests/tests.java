package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.Bank.Customer;
import com.revature.Exceptions.UserAlreadyExistsException;
import com.revature.Util.CustomerUtility;

public class tests {

	Customer customer = CustomerUtility.getCustomerFromUsername("yuri");

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@Test
	public void testDepositNegativeAmount() {
		assertEquals("Deposit must be greater than 0", CustomerUtility.makeDeposit(customer, -50));	
	}
	
	@Test
	public void testDepositPositiveAmount() {
		String result = CustomerUtility.makeDeposit(customer, 50);	
		if (result.contains("Your new balance is:")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	@Test
	public void testWithdrawNegativeAmount() {
		assertEquals("You cannot withdraw a negative Amount", CustomerUtility.makeWithdrawal(customer, -50));
	}
	
	@Test
	public void testWithdrawPositiveAmount() {
		String result = CustomerUtility.makeWithdrawal(customer, 50);	
		if (result.contains("Your new balance is:")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	@Test 
	public void testCustomerSignUp() throws UserAlreadyExistsException {
		assertTrue(CustomerUtility.attemptSignUp("customer2", "pass"));
	}
	/*
	 * See if method throws user already exists exception
	 */
	@Test
	public void testCustomerSignUpWithExistingUser() throws UserAlreadyExistsException {
		expectedException.expect(UserAlreadyExistsException.class);
		CustomerUtility.attemptSignUp("yuri", "pass");
	}
	
	

}
