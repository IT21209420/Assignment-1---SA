package com.airport.baggageeventpassengersubscriber;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.baggageeventpublisher.BaggageServicePublish;

import java.util.Scanner;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Baggage Event Passenger Subscriber Started!");
		serviceReference = context.getServiceReference(BaggageServicePublish.class.getName());
		BaggageServicePublish baggageCheckinServicePublish = (BaggageServicePublish) context.getService (serviceReference) ;
		
		displayMenu(baggageCheckinServicePublish);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Baggage Event Passenger Subscriber Stopped!");
		context.ungetService(serviceReference);
		
	}
	public void displayMenu(BaggageServicePublish baggageCheckinServicePublish) {
	    int option;
	    String loopThrough = "y";

	    Scanner scn = new Scanner(System.in);
	    System.out.println("\n\n");
	    System.out.println("---------- Baggage Management  - Passeneger ----------");
	    System.out.println("1  - Get Baggage Details By Passenger ID");
	    System.out.println("2  - Get Status of a Baggage");
	    System.out.println("3  - Inform Missing Baggage");
	    System.out.println("4  - Exit");

	    System.out.println();
	    System.out.print("Select an option : ");

	    try {
	        option = Integer.parseInt(scn.nextLine().trim());

	        switch (option) {
	            case 1:
	            	 try {
		                    baggageCheckinServicePublish.getBaggageDetailsById();
		                } catch (Exception e) {
		                    System.out.println("Error getting baggage details: " + e.getMessage());
		                }
		                while (loopThrough.equals("y")) {
		                    System.out.print("\n\nDo you want to Get Another Baggage (y/n) ? ");
		                    loopThrough = scn.nextLine().trim();
		                    if (loopThrough.equals("y")) {
		                        try {
		                            baggageCheckinServicePublish.getBaggageDetailsById();
		                        } catch (Exception e) {
		                            System.out.println("Error getting baggage details: " + e.getMessage());
		                        }
		                    }
		                }
		                
		                displayMenu(baggageCheckinServicePublish);
	          
	                break;
	            case 2:
	            	 try {
		                	baggageCheckinServicePublish.getBaggageStatusByPassengerID();
		                } catch (Exception e) {
		                    System.out.println("Error getting baggage status: " + e.getMessage());
		                }
		                
		                
		                    while (loopThrough.equals("y")) {
		                        System.out.print("\n\nDo you want to Get Another Baggage Status (y/n) ? ");
		                        loopThrough = scn.nextLine().trim();
		                        if (loopThrough.equals("y")) {
		                            baggageCheckinServicePublish.getBaggageStatusByPassengerID();
		                        }
		                    }
		               
		                displayMenu(baggageCheckinServicePublish);
		                break;
	            case 3:
	                try {
	                    baggageCheckinServicePublish.informMissingBaggage();
	                } catch (Exception e) {
	                    System.out.println("Error updating baggage status : " + e.getMessage());
	                }
	                while (loopThrough.equals("y")) {
	                    System.out.print("\n\nDo you want to Update Another Baggage Status as Missing (y/n) ? ");
	                    loopThrough = scn.nextLine().trim();
	                    if (loopThrough.equals("y")) {
	                        try {
	                            baggageCheckinServicePublish.informMissingBaggage();
	                        } catch (Exception e) {
	                            System.out.println("Error updating baggage status: " + e.getMessage());
	                        }
	                    }
	                    displayMenu(baggageCheckinServicePublish);
	                }
	                break;
	           
	            case 4:
	                System.out.println("Exited From the program.");
	                return;
	                
	            default:
	                System.out.println("Incorrect Input. Please Enter Valid Number...");
	                displayMenu(baggageCheckinServicePublish);
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid input. Please enter a valid number.");
	        displayMenu(baggageCheckinServicePublish);
	    }
	}



}
