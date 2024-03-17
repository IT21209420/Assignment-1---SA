package com.airport.baggageeventpublisher;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.airport.databasePublisher.Database;
import com.airport.databasePublisher.DatabaseImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BaggageServicePublishImpl implements BaggageServicePublish{
	Scanner scanner = new Scanner(System.in);
	ArrayList<Baggage> baggageList = new ArrayList<>();
	String missingStatus = "Missing";
	 private Connection connection = null;
	 private Statement statement = null;
	 private Database database;
	 private ResultSet resultSet;
	
	
	public BaggageServicePublishImpl() {
		super();
		Baggage baggage1 = new Baggage();
		baggage1.setBaggageID(1);
		baggage1.setPassengerID(101);
		baggage1.setFlightNUmber(123);
		baggage1.setWeight(20.5f);
		baggage1.setStatus("Checked");

		Baggage baggage2 = new Baggage();
		baggage2.setBaggageID(2);
		baggage2.setPassengerID(102);
		baggage2.setFlightNUmber(124);
		baggage2.setWeight(18.3f);
		baggage2.setStatus("Not checked");
		baggageList.add(baggage1);
		baggageList.add(baggage2);
		
		database = new DatabaseImpl();
	    connection = database.getDbConnection();
		
	}

	@Override
	public void addBaggage() throws InputMismatchException {
	    Baggage baggage = new Baggage();

	    try {
	        System.out.print("Enter Baggage ID : ");
	        int baggageID = scanner.nextInt();
	        if (baggageID <= 0) {
	            System.out.println("Invalid Baggage ID. Baggage ID must be a positive integer.");
	            return;
	        }
	        baggage.setBaggageID(baggageID);
	        
	        System.out.print("Enter Passenger ID : ");
	        int passengerID = scanner.nextInt();
	        if (passengerID <= 0) {
	            System.out.println("Invalid Passenger ID. Passenger ID must be a positive integer.");
	            return;
	        }
	        baggage.setPassengerID(passengerID);
	        
	        System.out.print("Enter Flight Number : ");
	        int flightNumber = scanner.nextInt();
	        if (flightNumber <= 0) {
	            System.out.println("Invalid Flight Number. Flight Number must be a positive integer.");
	            return;
	        }
	        baggage.setFlightNUmber(flightNumber);
	        
	        System.out.print("Enter Weight of the Baggage : ");
	        double weight = scanner.nextDouble();
	        if (weight <= 0) {
	            System.out.println("Invalid Weight. Weight must be a positive number.");
	            return;
	        }
	        baggage.setWeight(weight);
	        scanner.nextLine(); 
	        
	        System.out.print("Enter Status (Missing/Checked In/Checked Out) : ");
	        String status = scanner.nextLine().trim().toLowerCase();
	        if (!status.equals("missing") && !status.equals("checked in") && !status.equals("checked out")) {
	            System.out.println("Invalid Status. Status must be either Missing, Checked In, or Checked Out.");
	            return;
	        }
	        baggage.setStatus(status);

	        String sqlQuery = "INSERT INTO baggage(baggageId, passengerId, flightNumber, weight, status) "
	                + "VALUES('" + baggage.getBaggageID() + "', '" + baggage.getPassengerID() + "', '" + baggage.getFlightNumber()
	                + "', '" + baggage.getWeight() + "', '" + baggage.getStatus() + "')";

	        statement = connection.createStatement();
	        statement.executeUpdate(sqlQuery);
	        System.out.println("Baggage Created successfully...");

	    } catch (InputMismatchException e) {
	        throw new InputMismatchException("Invalid input. Please enter correct data types.");
	    } catch (SQLException e) {
	        throw new InputMismatchException("Error Creating Baggage!" + e.getMessage());
	    }
	}


    @Override
    public void getBaggageDetailsById() throws InputMismatchException {
        System.out.print("Enter Passenger ID : ");
        try {
	        int passengerID = scanner.nextInt();
	        System.out.print("Enter Baggage ID : ");
	        int baggageID = scanner.nextInt();
     
	        String sqlQueryPassenger = "SELECT * FROM baggage WHERE passengerId = '" + passengerID + "' AND baggageId = '"+baggageID+"'";
	       
	            statement = connection.createStatement();
	            resultSet = statement.executeQuery(sqlQueryPassenger);
	            System.out.println("\tBaggage ID\tPassenger ID\tFlight Number\tWeight\t\tStatus");
	            while (resultSet.next()) {
	                System.out.printf("%19d %16d %16d %8.2f %15s\n", resultSet.getInt("baggageId"), resultSet.getInt("passengerId"),
	                        resultSet.getInt("flightNumber"), resultSet.getDouble("weight"), resultSet.getString("status"));
	            
	            }
	        
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types." + e);
        }
         catch (SQLException e) {
          
            throw new InputMismatchException("Error searching passenger by ID." + e);
        }
    }
 

    @Override
    public void updateBaggageDetails() throws InputMismatchException {
        System.out.print("\nEnter Passenger ID to update details: ");
        try {
        int passengerID = scanner.nextInt();
        System.out.print("Enter Baggage ID : ");
        int baggageID = scanner.nextInt();
                
        String sqlQueryPassenger = "SELECT * FROM baggage WHERE passengerId = '" + passengerID + "' AND baggageId = '"+baggageID+"'";
	       
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQueryPassenger);
        
        Baggage baggage = new Baggage();
        while (resultSet.next()) {
        	
            System.out.println("\n-----Previous Baggage Detials-----\n");
            
            System.out.println("\tBaggage ID\tPassenger ID\tFlight Number\tWeight\t\tStatus");
            
            System.out.printf("%19d %16d %16d %8.2f %15s\n", resultSet.getInt("baggageId"), resultSet.getInt("passengerId"),
                    resultSet.getInt("flightNumber"), resultSet.getDouble("weight"), resultSet.getString("status")+"\n");
            
            baggage.setBaggageID(resultSet.getInt("baggageId"));
            baggage.setPassengerID(resultSet.getInt("passengerId"));
            baggage.setFlightNUmber(resultSet.getInt("flightNumber"));
            baggage.setWeight( resultSet.getDouble("weight"));
            baggage.setStatus(resultSet.getString("status"));  
            
            break;
        }
        if(baggage.getBaggageID() == 0) {  
        	System.out.println("Baggage not found!");
        	return;
        }
           
        System.out.print("\nEnter new Flight Number :");
        baggage.setFlightNUmber(scanner.nextInt());
        System.out.print("Enter new Weight of the Baggage :");
        baggage.setWeight(scanner.nextFloat());
        scanner.nextLine();
        System.out.print("Enter new Status :");
        baggage.setStatus(scanner.nextLine());
      
        String sqlUpdateQuery = "UPDATE baggage SET flightNumber = '" + baggage.getFlightNumber() + "', weight = '" + baggage.getWeight()  +  "', status = '" + baggage.getStatus()  +"' WHERE baggageId = '" + baggageID + "' AND passengerId = '"+passengerID+"'";
        
        statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate(sqlUpdateQuery);
        if (rowsAffected > 0) {
            System.out.println("Baggage details updated successfully...");
        } else {
            System.out.println("update failed...");
        }
            
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
        catch (SQLException e) {
            throw new InputMismatchException("Unable to Update Details!");
        }
    }

    @Override
    public void deleteBaggageDetails() throws InputMismatchException {
        System.out.print("Enter Passenger ID to delete details: ");
        try {
        int passengerID = scanner.nextInt();
        System.out.print("Enter Baggage ID : ");
        int baggageID = scanner.nextInt();
        String sqlDeleteQuery = "DELETE FROM baggage WHERE baggageId = '" + baggageID + "' AND "+" passengerId = '"+passengerID+"'";

        statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate(sqlDeleteQuery);
        if (rowsAffected > 0) {
            System.out.println("Baggage deleted successfully...");
        } else {
            System.out.println("Baggage not found or deletion failed...");
        }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types." + e);
        }
        catch (SQLException e) {
            throw new InputMismatchException("Error in deleting Baggage." + e);
        }
    }

    @Override
    public void updateBaggageStatusDetails() throws InputMismatchException {
        System.out.print("Enter Passenger ID to update status of Baggage: ");
        try {
        int passengerID = scanner.nextInt();
        System.out.print("Enter Baggage ID : ");
        int baggageID = scanner.nextInt();
        
        String sqlQueryPassenger = "SELECT status FROM baggage WHERE passengerId = '" + passengerID + "' AND baggageId = '"+baggageID+"'";
	       
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQueryPassenger);
        Baggage baggage = new Baggage();
        while (resultSet.next()) {
        	
            System.out.println("\n-----Previous Baggage Status-----\n");
            
            System.out.print("\tStatus : ");
            
            System.out.println( resultSet.getString("status")+"\n");
                    
                    
            baggage.setStatus(resultSet.getString("status"));  
            
            break;
        }
        if(baggage.getStatus() == "") {  
        	System.out.println("Baggage not found!");
        	return;
        }
        scanner.nextLine();
        System.out.print("Enter new Status :");
        baggage.setStatus(scanner.nextLine());
        
        String sqlUpdateQuery = "UPDATE baggage SET  status = '" + baggage.getStatus()  +"' WHERE baggageId = '" + baggageID + "' AND passengerId = '"+passengerID+"'";
        
        statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate(sqlUpdateQuery);
        if (rowsAffected > 0) {
            System.out.println("\nBaggage status updated successfully...");
        } else {
            System.out.println("\nupdate failed...");
        }
        
          
           
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
        catch (SQLException e) {
            throw new InputMismatchException("Error in updating Baggage status." + e);
        }
    }
    
    @Override
    public void getBaggageStatusByPassengerID() throws InputMismatchException {
      
        
        System.out.print("Enter Passenger ID to get status of Baggage: ");
        try {
	        int passengerID = scanner.nextInt();
	        System.out.print("Enter Baggage ID : ");
	        int baggageID = scanner.nextInt();
     
	        String sqlQueryPassenger = "SELECT status FROM baggage WHERE passengerId = '" + passengerID + "' AND baggageId = '"+baggageID+"'";
	       
	            statement = connection.createStatement();
	            resultSet = statement.executeQuery(sqlQueryPassenger);
	            
	            while (resultSet.next()) {
	            	System.out.print("\tStatus : ");
	                System.out.print(resultSet.getString("status"));
	            
	            }
	        
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types." + e);
        }
         catch (SQLException e) {
          
            throw new InputMismatchException("Error searching passenger by ID." + e);
        }
    }

	@Override
	public void informMissingBaggage() throws InputMismatchException{
		 

		 System.out.print("Enter Passenger ID to update status of Baggage: ");
	        try {
	        int passengerID = scanner.nextInt();
	        System.out.print("Enter Baggage ID : ");
	        int baggageID = scanner.nextInt();
	        
	        String sqlQueryPassenger = "SELECT status FROM baggage WHERE passengerId = '" + passengerID + "' AND baggageId = '"+baggageID+"'";
		       
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(sqlQueryPassenger);
	        Baggage baggage = new Baggage();
	        while (resultSet.next()) {
	        	
	            System.out.println("\n-----Previous Baggage Status-----\n");
	            
	            System.out.print("\tStatus : ");
	            
	            System.out.println( resultSet.getString("status")+"\n");
	                    
	            baggage.setStatus(resultSet.getString("status"));  
	            
	            break;
	        }
	        
	        
	        
	        String sqlUpdateQuery = "UPDATE baggage SET  status = '" + missingStatus  +"' WHERE baggageId = '" + baggageID + "' AND passengerId = '"+passengerID+"'";
	        
	        statement = connection.createStatement();
	        int rowsAffected = statement.executeUpdate(sqlUpdateQuery);
	        if (rowsAffected > 0) {
	            System.out.println("\nBaggage status updated successfully...");
	        } else {
	            System.out.println("\nupdate failed...");
	        }
	        
	          
	           
	        } catch (InputMismatchException e) {
	            throw new InputMismatchException("Invalid input. Please enter correct data types.");
	        }
	        catch (SQLException e) {
	            throw new InputMismatchException("Error in updating Baggage status." + e);
	        }
	}

	@Override
	public void getAllMissingBaggeDetails() throws InputMismatchException {
       int count = 0;
        try {
       
        	  String sqlQueryAllPassengers = "SELECT * FROM baggage WHERE status = '"+missingStatus+"'";
        	  statement = connection.createStatement();
  	          resultSet = statement.executeQuery(sqlQueryAllPassengers);
  	          System.out.println("\tBaggage ID\tPassenger ID\tFlight Number\tWeight\t\tStatus");
  	        while (resultSet.next()) {
  	        	count++;
              System.out.printf("%19d %16d %16d %8.2f %15s\n", resultSet.getInt("baggageId"), resultSet.getInt("passengerId"),
                    resultSet.getInt("flightNumber"), resultSet.getDouble("weight"), resultSet.getString("status")+"\n");
  	        }
  	        if(count<=0) {
  	        	System.out.println("No Missing Baggages!");
  	        }
  	        
        } catch (SQLException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
    }
}
