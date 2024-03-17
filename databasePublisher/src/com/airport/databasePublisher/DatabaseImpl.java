package com.airport.databasePublisher;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseImpl implements Database{

	private Connection connection;
	private final String driverName;
	private String connectionString;
	private String databaseUser;
	private String databasePassword;
	
	public DatabaseImpl() {
		this.driverName = "com.mysql.cj.jdbc.Driver";
		this.connectionString = "jdbc:mysql://localhost:3306/airport";
		this.databaseUser = "root";
		this.databasePassword = "Hasa@123456";
	}

	@Override
	public Connection getDbConnection() {
		if(connection == null) {
			synchronized (DatabaseImpl.class) {
				if(connection == null) {
					try {
						Class.forName(driverName);
						this.connection =(Connection) DriverManager.getConnection(connectionString, databaseUser, databasePassword);
					} catch (ClassNotFoundException exc) {
						System.out.println("Class not found");
						System.out.println(exc.getMessage());
					} catch (SQLException exc) {
						System.out.println("SQL Error");
						System.out.println(exc.getMessage());
					}
					System.out.println("DB Connection Successfull");
				}
			}
		}

	return connection;
	}

}
