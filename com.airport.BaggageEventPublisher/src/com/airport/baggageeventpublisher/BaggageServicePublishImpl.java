package com.airport.baggageeventpublisher;

import java.util.Scanner;

public class BaggageServicePublishImpl implements BaggageServicePublish{
	Scanner scanner = new Scanner(System.in);
	
	@Override
	
	public void addBaggage() {
		Baggage baggage = new Baggage();
		
		System.out.print("Enter Baggage ID : ");
		baggage.setBaggageID(scanner.nextInt());
		System.out.print("Enter Passenger ID ");
		baggage.setPassengerID(scanner.nextInt());
		System.out.print("Enter Flight Number");
		baggage.setFlightNUmber(scanner.nextInt());
		System.out.print("Enter Weight of the Baggage ");
		baggage.setWeight(scanner.nextFloat());
		System.out.print("Enter Passenger ID ");
		baggage.setStatus(scanner.nextLine());
		
		
		
		
		
	}

}
