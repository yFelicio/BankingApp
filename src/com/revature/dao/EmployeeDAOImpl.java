package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Util.ConnectionFactory;
import com.revature.pojo.Admin;
import com.revature.pojo.Customer;
import com.revature.pojo.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	PreparedStatement stmt = null;
	
	@Override
	public Employee logIn(String username, String password) {
		Employee employee = null;
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "SELECT * FROM USERS WHERE TYPE_ID_FK != 1 AND USERNAME = ? AND PASSWORD = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();	
			while (rs.next()) {
				if (rs.getInt("TYPE_ID_FK") == 2) {
					employee = new Employee();
				} else if (rs.getInt("TYPE_ID_FK") == 3) {
					employee = new Admin();
				}
				employee.setEmployeeID(rs.getInt("USER_ID"));
				employee.setUsername(rs.getString("USERNAME"));
				employee.setPassword(rs.getString("PASSWORD"));
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return employee;
	}

}
