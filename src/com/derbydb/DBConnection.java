package com.derbydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/FIGHTCLUB";
    private static final String DB_USER = "item";
    private static final String DB_PASSWORD = "item";
    
	  public static Connection getDBConnection() {
	         Connection dbConnection = null;
	         try {
	             Class.forName(DB_DRIVER);
	         } catch (ClassNotFoundException e) {
	             System.out.println(e.getMessage());
	         }
	         try {
	             dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
	                     DB_PASSWORD);
	             return dbConnection;
	         } catch (SQLException e) {
	             System.out.println(e.getMessage());
	         }
	         return dbConnection;
	     }
}
