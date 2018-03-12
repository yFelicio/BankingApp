package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Util.ConnectionFactory;
import com.revature.pojo.Application;

public class ApplicationDAOImpl implements ApplicationDAO {

	PreparedStatement stmt = null;
	
	@Override
	public void createApplication(int customer_id) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			// get next application_id
			String sql = "SELECT APPLICATION_ID_SEQ.NEXTVAL FROM DUAL";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			int application_id = 0;
			while (rs.next()) {
				application_id = rs.getInt(1);
			}
			rs.close();
			
			// insert into applications & users_applications tables
			sql = "INSERT INTO APPLICATIONS VALUES(?,?,?)";	
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, application_id);
			stmt.setString(2, "PENDING");
			stmt.setInt(3,customer_id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	

	@Override
	public List<Application> getAllApplications() {
		List<Application> applications = new ArrayList<>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM APPLICATIONS";
		
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				Application application = new Application();
				application.setApplicationID(rs.getInt("APPLICATION_ID"));
				application.setStatus(rs.getString("STATUS"));
				application.setUserID(rs.getInt("USER_ID_FK"));
				applications.add(application);
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return applications;
		
	}


	@Override
	public void updateApplication(Application application) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "UPDATE APPLICATIONS SET STATUS = ? WHERE APPLICATION_ID = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, application.getStatus());
			stmt.setInt(2, application.getApplicationID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	@Override
	public List<Application> getApplicationFromUser(int customer_id) {
		List<Application> applications = new ArrayList<>();
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM APPLICATIONS WHERE USER_ID_FK = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer_id);
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				Application application = new Application();
				application.setApplicationID(rs.getInt("APPLICATION_ID"));
				application.setStatus(rs.getString("STATUS"));
				application.setUserID(rs.getInt("USER_ID_FK"));
				applications.add(application);
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return applications;
	
	}

}
