package com.revature.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class LoggingUtil {
	
	private static Logger log = Logger.getRootLogger();
	
	public static void logFatal(String s) {
		log.fatal(s);
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO LOGS (LOG_TIME, LOG_MESSAGE) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			    
			stmt.setString(1, formatter.format(date));
			stmt.setString(2, "fatal: " + s);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void logError(String s) {
		log.error(s);
Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO LOGS (LOG_TIME, LOG_MESSAGE) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			    
			stmt.setString(1, formatter.format(date));
			stmt.setString(2, "Error: " + s);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void logInfo(String s) {
		log.info(s);
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO LOGS (LOG_TIME, LOG_MESSAGE) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();   
			    
			stmt.setString(1, formatter.format(date));
			stmt.setString(2, "Info: " + s);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void logWarn(String s) {
		log.warn(s);
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO LOGS (LOG_TIME, LOG_MESSAGE) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			    
			stmt.setString(1, formatter.format(date));
			stmt.setString(2, "Warn: " + s);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void logDebug(String s) {
		log.debug(s);
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO LOGS (LOG_TIME, LOG_MESSAGE) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			    
			stmt.setString(1, formatter.format(date));
			stmt.setString(2, "Debug: " + s);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void logTrace(String s) {
		log.trace(s);
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			String sql = "INSERT INTO LOGS (LOG_TIME, LOG_MESSAGE) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			    
			stmt.setString(1, formatter.format(date));
			stmt.setString(2, "Trace: " + s);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
