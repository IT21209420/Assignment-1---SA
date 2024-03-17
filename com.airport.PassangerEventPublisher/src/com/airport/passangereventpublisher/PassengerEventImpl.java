package com.airport.passangereventpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.airport.databasePublisher.*;

public class PassengerEventImpl implements PassengerEvent {

    private Connection connection = null;
    private Statement statement = null;
    private Database database;
    private ResultSet resultSet;

    public PassengerEventImpl() {
        super();
        database = new DatabaseImpl();
        connection = database.getDbConnection();
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void bookPassenger() {
        Passenger passenger = new Passenger();

        System.out.println("Enter Passenger Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Passenger name cannot be empty. Aborting...");
            return;
        }
        passenger.setName(name);

        System.out.println("Enter Passenger Age: ");
        String ageInput = scanner.nextLine().trim();
        if (ageInput.isEmpty()) {
            System.out.println("Passenger age cannot be empty. Aborting...");
            return;
        }
        int age;
        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format. Please enter a valid number.");
            return;
        }
        passenger.setAge(age);

        System.out.println("Enter Passenger Gender: ");
        String gender = scanner.nextLine().trim();
        if (gender.isEmpty()) {
            System.out.println("Passenger gender cannot be empty. Aborting...");
            return;
        }
        passenger.setGender(gender);

        System.out.println("Enter Passenger Phone Number: ");
        String phoneNumber = scanner.nextLine().trim();
        if (phoneNumber.isEmpty()) {
            System.out.println("Passenger phone number cannot be empty. Aborting...");
            return;
        }
        passenger.setPhoneNumber(phoneNumber);

        String sqlQueryPassenger = "INSERT INTO passenger(name, age, gender, phone_number) "
                + "VALUES('" + passenger.getName() + "', '" + passenger.getAge() + "', '" + passenger.getGender()
                + "', '" + passenger.getPhoneNumber() + "')";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQueryPassenger);
            System.out.println("Passenger booked successfully...");
        } catch (SQLException exc) {
            System.out.println("Error booking passenger");
            System.out.println(exc.getMessage());
        }
    }


    @Override
    public void searchPassenger() {
        int id;

        System.out.println("Enter Passenger ID: ");
        id = Integer.parseInt(scanner.nextLine().trim());

        String sqlQueryPassenger = "SELECT * FROM passenger WHERE id = '" + id + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQueryPassenger);
            System.out.println("\tPassenger ID\tName\tAge\tGender\tPhone Number");
            while (resultSet.next()) {
                System.out.printf("%10d %20s %10d %10s %20s\n", resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getString("gender"), resultSet.getString("phone_number"));
            }
        } catch (SQLException exc) {
            System.out.println("Error searching passenger by ID");
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void updatePassenger() {
        int id;

        System.out.println("Enter Passenger ID to update: ");
        id = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Select field to update:");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        String fieldName = "";
        String newValue = "";

        switch (choice) {
            case 1:
                fieldName = "name";
                System.out.println("Enter New Name: ");
                newValue = scanner.nextLine().trim();
                break;
            case 2:
                fieldName = "phone_number";
                System.out.println("Enter New Phone Number: ");
                newValue = scanner.nextLine().trim();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String sqlUpdateQuery = "UPDATE passenger SET " + fieldName + " = '" + newValue + "' WHERE id = '" + id + "'";

        try {
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sqlUpdateQuery);
            if (rowsAffected > 0) {
                System.out.println("Passenger details updated successfully...");
            } else {
                System.out.println("Passenger not found or update failed...");
            }
        } catch (SQLException exc) {
            System.out.println("Error updating passenger details");
            System.out.println(exc.getMessage());
        }
    }

	@Override
	public void getAllPassengers() {
	    String sqlQueryAllPassengers = "SELECT * FROM passenger";

	    try {
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(sqlQueryAllPassengers);
	        System.out.println("\tPassenger ID\tName\tAge\tGender\tPhone Number");
	        while (resultSet.next()) {
	            System.out.printf("%10d %20s %10d %10s %20s\n", resultSet.getInt("id"), resultSet.getString("name"),
	                    resultSet.getInt("age"), resultSet.getString("gender"), resultSet.getString("phone_number"));
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error getting all passengers");
	        System.out.println(exc.getMessage());
	    }
	}
	
	@Override
	public void deletePassenger() {
	    int id;

	    System.out.println("Enter Passenger ID to delete : ");
	    id = Integer.parseInt(scanner.nextLine().trim());

	    String sqlDeleteQuery = "DELETE FROM passenger WHERE id = '" + id + "'";

	    try {
	        statement = connection.createStatement();
	        int rowsAffected = statement.executeUpdate(sqlDeleteQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Passenger deleted successfully...");
	        } else {
	            System.out.println("Passenger not found or deletion failed...");
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error deleting passenger");
	        System.out.println(exc.getMessage());
	    }
	}
	
	@Override
	public void assignSeat() {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Enter Passenger ID: ");
	    int passengerId = Integer.parseInt(scanner.nextLine().trim());
	    
	    System.out.println("Enter Seat Number: ");
	    String seatNumber = scanner.nextLine().trim();

	    SeatAssignment seatAssignment = new SeatAssignment();
	    seatAssignment.setPassengerId(passengerId);
	    seatAssignment.setSeatNumber(seatNumber);

	    String sqlQuerySeatAssignment = "INSERT INTO seat_assignment(passenger_id, seat_number) "
	            + "VALUES('" + seatAssignment.getPassengerId() + "', '" + seatAssignment.getSeatNumber() + "')";

	    try {
	        statement = connection.createStatement();
	        statement.executeUpdate(sqlQuerySeatAssignment);
	        System.out.println("Seat assigned successfully...");
	    } catch (SQLException exc) {
	        System.out.println("Error assigning seat");
	        System.out.println(exc.getMessage());
	    }
	}

	@Override
	public void getSeatAssignment() {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Enter Passenger ID: ");
	    int passengerId = Integer.parseInt(scanner.nextLine().trim());

	    String sqlQuerySeatAssignment = "SELECT * FROM seat_assignment WHERE passenger_id = '" + passengerId + "'";

	    try {
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(sqlQuerySeatAssignment);
	        System.out.println("\tPassenger ID\tSeat Number");
	        while (resultSet.next()) {
	            System.out.printf("%10d %20s\n", resultSet.getInt("passenger_id"), resultSet.getString("seat_number"));
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error getting seat assignment");
	        System.out.println(exc.getMessage());
	    }
	}
	
	@Override
	public void updateSeatNumber() {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Enter Passenger ID: ");
	    int passengerId = Integer.parseInt(scanner.nextLine().trim());
	    
	    System.out.println("Enter New Seat Number: ");
	    String newSeatNumber = scanner.nextLine().trim();

	    String sqlUpdateQuery = "UPDATE seat_assignment SET seat_number = '" + newSeatNumber + "' WHERE passenger_id = '" + passengerId + "'";

	    try {
	        statement = connection.createStatement();
	        int rowsAffected = statement.executeUpdate(sqlUpdateQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Seat number updated successfully...");
	        } else {
	            System.out.println("Passenger not found or update failed...");
	        }
	    } catch (SQLException exc) {
	        System.out.println("Error updating seat number");
	        System.out.println(exc.getMessage());
	    }
	}


	

    
}
