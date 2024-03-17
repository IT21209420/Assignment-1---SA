package com.airport.newflighteventpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.airport.databasePublisher.*;



public class NewFlightEventPublishImpl  implements NewFlightEventPublish{
	
	private Connection connection = null;
    private Statement statement = null;
    private DatabaseImpl database;
    private ResultSet resultSet;
	
	public NewFlightEventPublishImpl() {
		super();
		database = new DatabaseImpl();
        connection = database.getDbConnection();
	}
	
	Scanner scanner = new Scanner(System.in);

	@Override
	public void publishNewFlightEvent() {
	    FlightEvent flightEvent = new FlightEvent();
	    
	    System.out.println("Enter Flight Number: ");
	    String flightNumber = scanner.nextLine().trim();
	    if (flightNumber.isEmpty()) {
	        System.out.println("Flight number cannot be empty. Aborting...");
	        return;
	    }
	    flightEvent.setFlightNumber(flightNumber);
	    
	    System.out.println("Enter Flight Name: ");
	    String flightName = scanner.nextLine().trim();
	    if (flightName.isEmpty()) {
	        System.out.println("Flight name cannot be empty. Aborting...");
	        return;
	    }
	    flightEvent.setFlightName(flightName);

	    System.out.println("Enter Destination: ");
	    String destination = scanner.nextLine().trim();
	    if (destination.isEmpty()) {
	        System.out.println("Destination cannot be empty. Aborting...");
	        return;
	    }
	    flightEvent.setDestination(destination);

	    System.out.println("Enter Departure Time (YYYY-MM-DD HH:MM:SS): ");
	    String departureTime = scanner.nextLine().trim();
	    if (departureTime.isEmpty()) {
	        System.out.println("Departure time cannot be empty. Aborting...");
	        return;
	    }
	    flightEvent.setDepartureTime(departureTime);

	    System.out.println("Enter Flight Status: ");
	    String status = scanner.nextLine().trim();
	    if (status.isEmpty()) {
	        System.out.println("Flight status cannot be empty. Aborting...");
	        return;
	    }
	    flightEvent.setStatus(status);
	    
	    String sqlQueryFlightEvent = "INSERT INTO flightevent(flightNumber, flightName, destination, departureTime, status) "
	            + "VALUES('" + flightEvent.getFlightNumber() + "', '" + flightEvent.getFlightName() + "', '" + flightEvent.getDestination() + "', '"
	            + flightEvent.getDepartureTime() + "', '" + flightEvent.getStatus() + "')";

	    try {
	        statement = connection.createStatement();
	        statement.executeUpdate(sqlQueryFlightEvent);
	        System.out.println("Flight event published successfully...");
	    } catch (SQLException exc) {
	        System.out.println("Error publishing flight event");
	        System.out.println(exc.getMessage());
	    }
	}



	@Override
	public void searchFlightEvent() {
	    int id;
	    
	    System.out.println("Enter Flight Event ID: ");
	    id = Integer.parseInt(scanner.nextLine().trim());
	    
	    String sqlQueryFlightEvent = "SELECT * FROM flightevent WHERE id = '" + id + "'";

	    try {
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(sqlQueryFlightEvent);
	        System.out.println("\tFlight Event ID\t\tFlight Number\t\tFlight Name\t\tDestination\t\tDeparture Time\t\tStatus");
	        while (resultSet.next()) {
	            System.out.printf("%20d %20s %20s %20s %20s %20s\n", resultSet.getInt("id"), resultSet.getString("flightNumber"),
	                    resultSet.getString("flightName"), resultSet.getString("destination"), resultSet.getString("departureTime"),
	                    resultSet.getString("status"));
	        }

	    } catch (SQLException exc) {
	        System.out.println("Error searching flight event by ID");
	        System.out.println(exc.getMessage());
	    }
	}
	
	@Override
	public void updateFlightEvent() {
	    int id;

	    System.out.println("Enter Flight Event ID to update: ");
	    id = Integer.parseInt(scanner.nextLine().trim());

	    System.out.println("Enter New Departure Time (YYYY-MM-DD HH:MM:SS): ");
	    String newDepartureTime = scanner.nextLine().trim();

	    System.out.println("Enter New Status: ");
	    String newStatus = scanner.nextLine().trim();

	    String sqlUpdateQuery = "UPDATE flightevent SET departureTime = '" + newDepartureTime + "', status = '" + newStatus + "' WHERE id = '" + id + "'";

	    try {
	        statement = connection.createStatement();
	        int rowsAffected = statement.executeUpdate(sqlUpdateQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Flight event updated successfully...");
	        } else {
	            System.out.println("Flight event not found or update failed...");
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error updating flight event");
	        System.out.println(exc.getMessage());
	    }
	}

	@Override
	public void getAllFlightEvents() {
	    String sqlQueryAllFlightEvents = "SELECT * FROM flightevent";

	    try {
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(sqlQueryAllFlightEvents);
	        System.out.println("\tFlight Event ID\tFlight Number\tFlight Name\tDestination\tDeparture Time\tStatus");
	        while (resultSet.next()) {
	            System.out.printf("%20d %20s %20s %20s %20s %20s\n", resultSet.getInt("id"), resultSet.getString("flightNumber"),
	                    resultSet.getString("flightName"), resultSet.getString("destination"), resultSet.getString("departureTime"), resultSet.getString("status"));
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error getting all flight events");
	        System.out.println(exc.getMessage());
	    }
	}
	
	@Override
	public void deleteFlightEvent() {
	    int id;

	    System.out.println("Enter Flight Event ID to delete: ");
	    id = Integer.parseInt(scanner.nextLine().trim());

	    String sqlDeleteQuery = "DELETE FROM flightevent WHERE id = '" + id + "'";

	    try {
	        statement = connection.createStatement();
	        int rowsAffected = statement.executeUpdate(sqlDeleteQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Flight event deleted successfully...");
	        } else {
	            System.out.println("Flight event not found or delete failed...");
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error deleting flight event");
	        System.out.println(exc.getMessage());
	    }
	}
	
	@Override
	public void assignCrewToFlight() {
	    int flightId;

	    System.out.println("Enter Flight Event ID to assign crew: ");
	    flightId = Integer.parseInt(scanner.nextLine().trim());

	    boolean addMoreCrew = true;

	    while (addMoreCrew) {
	        Crew crew = new Crew();

	        System.out.println("Enter Crew Member Name: ");
	        crew.setName(scanner.nextLine().trim());

	        System.out.println("Enter Crew Position: ");
	        crew.setPosition(scanner.nextLine().trim());

	        // Save crew details to the database
	        String sqlQueryCrew = "INSERT INTO crew(name, position, flightId) "
	                + "VALUES('" + crew.getName() + "', '" + crew.getPosition() + "', '" + flightId + "')";

	        try {
	            statement = connection.createStatement();
	            statement.executeUpdate(sqlQueryCrew);
	            System.out.println("Crew assigned to the flight successfully...");
	        } catch (SQLException exc) {
	            System.out.println("Error assigning crew to the flight");
	            System.out.println(exc.getMessage());
	        }

	        // Ask if there are more crew members to add
	        System.out.println("Do you want to assign another crew member? (yes/no): ");
	        String choice = scanner.nextLine().trim().toLowerCase();
	        if (!choice.equals("yes")) {
	            addMoreCrew = false;
	        }
	    }
	}

	

	@Override
	public void getAllCrew() {
	    int flightId;

	    System.out.println("Enter Flight Event ID to see assigned crew: ");
	    flightId = Integer.parseInt(scanner.nextLine().trim());

	    String sqlQueryAllCrew = "SELECT * FROM crew WHERE flightId = '" + flightId + "'";

	    try {
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(sqlQueryAllCrew);
	        System.out.println("\tCrew ID\t\tName\t\tPosition");
	        while (resultSet.next()) {
	            System.out.printf("%10d %20s %20s\n", resultSet.getInt("id"), resultSet.getString("name"),
	                    resultSet.getString("position"));
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error getting crew members for the specified flight");
	        System.out.println(exc.getMessage());
	    }
	}




	
	

}
