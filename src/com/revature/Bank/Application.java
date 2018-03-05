package com.revature.Bank;

import java.io.Serializable;
import java.util.UUID;

public class Application implements Serializable {

	private final UUID applicationID;
	private String status;
	
	public Application() {
		applicationID = UUID.randomUUID();
		status = "pending";
	}
	public Application(String status) {
		applicationID = UUID.randomUUID();
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public void createAccount() {
		
	}
	public UUID getApplicationID() {
		return applicationID;
	}
	
	
}
