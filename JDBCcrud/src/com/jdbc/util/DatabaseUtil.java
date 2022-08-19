package com.jdbc.util;

public class DatabaseUtil {
	
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.mysql";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbcnewdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	public DatabaseUtil() {
		try {
		Class.forName("DRIVER_PATH");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong" + e);
		}
	}
	
	public void getConnection() throws SQL Exception{
		DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
	}
}
