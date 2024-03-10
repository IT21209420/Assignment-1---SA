package com.airport.baggageeventpublisher;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BaggageServicePublishImpl implements BaggageServicePublish{
	Scanner scanner = new Scanner(System.in);
	ArrayList<Baggage> baggageList = new ArrayList<>();
	String missingStatus = "Missing";
	
	
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
		
	}

	@Override
    public void addBaggage() throws InputMismatchException {
        Baggage baggage = new Baggage();

        try {
            System.out.print("Enter Baggage ID : ");
            baggage.setBaggageID(scanner.nextInt());
            System.out.print("Enter Passenger ID :");
            baggage.setBaggageID(scanner.nextInt());
            System.out.print("Enter Flight Number :");
            baggage.setFlightNUmber(scanner.nextInt());
            System.out.print("Enter Weight of the Baggage :");
            baggage.setWeight(scanner.nextFloat());
            scanner.nextLine();
            System.out.print("Enter Status :");
            baggage.setStatus(scanner.nextLine());

            baggageList.add(baggage);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
    }

    @Override
    public void getBaggageDetailsById() throws InputMismatchException {
        System.out.print("Enter Passenger ID : ");
        try {
	        int passengerID = scanner.nextInt();
	        for (Baggage baggage : baggageList) {
	            if (baggage.getPassengerID() == passengerID)
	                System.out.println(baggage.toString());
	        }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
    }

    @Override
    public void updateBaggageDetails() throws InputMismatchException {
        System.out.print("Enter Passenger ID to update details: ");
        try {
        int passengerID = scanner.nextInt();

            for (Baggage baggage : baggageList) {
                if (baggage.getPassengerID() == passengerID) {
                    System.out.println("-----Previous Baggage Detials-----");
                    System.out.println();
                    System.out.println(baggage.toString());
                    System.out.println();
                    System.out.println("Updating details for passenger ID: " + passengerID);
                    System.out.print("Enter new Flight Number :");
                    baggage.setFlightNUmber(scanner.nextInt());
                    System.out.print("Enter new Weight of the Baggage :");
                    baggage.setWeight(scanner.nextFloat());
                    System.out.print("Enter new Status :");
                    baggage.setStatus(scanner.next());
                    System.out.println("Baggage details updated successfully.");
                    return;
                }
            }
            System.out.println("Passenger with ID " + passengerID + " not found.");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
    }

    @Override
    public void deleteBaggageDetails() throws InputMismatchException {
        System.out.print("Enter Passenger ID to delete details: ");
        try {
        int passengerID = scanner.nextInt();
            for (int i = 0; i < baggageList.size(); i++) {
                Baggage baggage = baggageList.get(i);
                if (baggage.getPassengerID() == passengerID) {
                    baggageList.remove(i);
                    System.out.println("Baggage details deleted successfully.");
                    return;
                }
            }
            System.out.println("Passenger with ID " + passengerID + " not found.");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
    }

    @Override
    public void updateBaggageStatusDetails() throws InputMismatchException {
        System.out.print("Enter Passenger ID to update status of Baggage: ");
        try {
        int passengerID = scanner.nextInt();
            for (int i = 0; i < baggageList.size(); i++) {
                Baggage baggage = baggageList.get(i);
                if (baggage.getPassengerID() == passengerID) {
                    System.out.println("-----Previous Baggage Status-----");
                    System.out.println();
                    System.out.println(baggage.getStatus());
                    System.out.println();
                    System.out.println("Updating status for passenger ID: " + passengerID);
                    System.out.print("Enter new Status :");
                    baggage.setStatus(scanner.next());
                    System.out.println("Baggage details updated successfully.");
                    return;
                }
            }
            System.out.println("Passenger with ID " + passengerID + " not found.");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
    }
    
    @Override
    public void getBaggageStatusByPassengerID() throws InputMismatchException {
        System.out.print("Enter Passenger ID to get status of Baggage: ");
        try {
        int passengerID = scanner.nextInt();
            for (int i = 0; i < baggageList.size(); i++) {
                Baggage baggage = baggageList.get(i);
                if (baggage.getPassengerID() == passengerID) {
                    System.out.println("-----Baggage Status-----");
                    System.out.println();
                    System.out.println(baggage.getStatus());
                    System.out.println();
                    
                    return;
                }
            }
            System.out.println("Passenger with ID " + passengerID + " not found.");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please enter correct data types.");
        }
    }

	@Override
	public void informMissingBaggage() throws InputMismatchException{
		 System.out.print("Enter Passenger ID of Baggage to update status as 'Missing' : ");
		 try {
	        int passengerID = scanner.nextInt();
	            for (int i = 0; i < baggageList.size(); i++) {
	                Baggage baggage = baggageList.get(i);
	                if (baggage.getPassengerID() == passengerID) {                
	                    baggage.setStatus(missingStatus);
	                    System.out.println();
	                    System.out.println("Updated status for passenger ID " + passengerID +" Successfully!");
	                   
	                    return;
	                }
	            }
	            System.out.println("Passenger with ID " + passengerID + " not found.");
	        } catch (InputMismatchException e) {
	            throw new InputMismatchException("Invalid input. Please enter correct data types.");
	        }
		
	}

	@Override
	public void getAllMissingBaggeDetails() {
		int count = 0;
		
		 try {
	        
	            for (int i = 0; i < baggageList.size(); i++) {
	                Baggage baggage = baggageList.get(i);
	                if (baggage.getStatus().trim().equals(missingStatus)) {  
	                	count++;
	                    System.out.println("-----Missing Baggage Details-----");
	                    System.out.println(baggage.toString());
	                    System.out.println();
	                    return;
	                }
	            }
	            if(count<=0) {
	            	System.out.println("No Missing Baggages!");
	            }
	           
	        } catch (InputMismatchException e) {
	            throw new InputMismatchException("Invalid input. Please enter correct data types.");
	        }
		
	}

}
