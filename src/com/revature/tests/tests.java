package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.Bank.Customer;
import com.revature.Util.CustomerUtility;

public class tests {

	Customer customer = CustomerUtility.getCustomerFromUsername("yuri");
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void testDepositNegativeAmount() {
		
		AssertEquals("deposit must be greater than zero",CustomerUtility.makeDeposit(customer, -50));
		
	}

}
