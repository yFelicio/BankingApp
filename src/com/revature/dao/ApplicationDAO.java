package com.revature.dao;

import java.util.List;

import com.revature.pojo.Application;

public interface ApplicationDAO {

	public void createApplication(int customer_id);
	public List<Application> getApplicationFromUser(int customer_id);
	public List<Application> getAllApplications();
	public void updateApplication(Application application);
	
}
