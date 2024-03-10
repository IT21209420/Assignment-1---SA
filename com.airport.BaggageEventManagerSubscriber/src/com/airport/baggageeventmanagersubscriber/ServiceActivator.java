package com.airport.baggageeventmanagersubscriber;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.baggageeventpublisher.BaggageServicePublish;

import java.util.Scanner;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		
		serviceReference = context.getServiceReference(BaggageServicePublish.class.getName());
		BaggageServicePublish baggageCheckinServicePublish = (BaggageServicePublish) context.getService (serviceReference) ;
		
		displayMenu(baggageCheckinServicePublish);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye !!!");
		context.ungetService(serviceReference);
		
	}
	public void displayMenu(BaggageServicePublish baggageCheckinServicePublish) {
	    int option;
	    String loopThrough = "y";

	    Scanner scn = new Scanner(System.in);
	    System.out.println("\n\n");
	    System.out.println("---------- Baggage Management  - Manager ----------");
	    System.out.println("1  - Add Baggage Details");
	    System.out.println("2  - Get Baggage By Passenger ID");
	    System.out.println("3  - Update Baggage By Passenger ID");
	    System.out.println("4  - Delete Baggage By Passenger ID");
	    System.out.println("5  - Update Status of a Baggage");
	    System.out.println("6  - Get All Missing Baggages");
	    System.out.println("7  - Exit");

	    System.out.println();
	    System.out.print("Select an option : ");

	    try {
	        option = Integer.parseInt(scn.nextLine().trim());

	        switch (option) {
	            case 1:
	                try {
	                    baggageCheckinServicePublish.addBaggage();
	                } catch (Exception e) {
	                    System.out.println("Error adding baggage details: " + e.getMessage());
	                }
	                while (loopThrough.equals("y")) {
	                    System.out.print("\n\nDo you want to Add Another Baggage (y/n) ? ");
	                    loopThrough = scn.nextLine().trim();

	                    if (loopThrough.equals("y")) {
	                        try {
	                            baggageCheckinServicePublish.addBaggage();
	                        } catch (Exception e) {
	                            System.out.println("Error adding baggage details: " + e.getMessage());
	                        }
	                    }
	                }
	                displayMenu(baggageCheckinServicePublish);
	                break;
	            case 2:
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
	            case 3:
	                try {
	                    baggageCheckinServicePublish.updateBaggageDetails();
	                } catch (Exception e) {
	                    System.out.println("Error updating baggage details: " + e.getMessage());
	                }
	                while (loopThrough.equals("y")) {
	                    System.out.print("\n\nDo you want to Update Another Baggage (y/n) ? ");
	                    loopThrough = scn.nextLine().trim();
	                    if (loopThrough.equals("y")) {
	                        try {
	                            baggageCheckinServicePublish.updateBaggageDetails();
	                        } catch (Exception e) {
	                            System.out.println("Error updating baggage details: " + e.getMessage());
	                        }
	                    }
	                }
	                displayMenu(baggageCheckinServicePublish);
	                break;
	            case 4:
	                try {
	                    baggageCheckinServicePublish.deleteBaggageDetails();
	                } catch (Exception e) {
	                    System.out.println("Error deleting baggage details: " + e.getMessage());
	                }
	                while (loopThrough.equals("y")) {
	                    System.out.print("\n\nDo you want to Delete Another Baggage (y/n) ? ");
	                    loopThrough = scn.nextLine().trim();
	                    if (loopThrough.equals("y")) {
	                        try {
	                            baggageCheckinServicePublish.deleteBaggageDetails();
	                        } catch (Exception e) {
	                            System.out.println("Error deleting baggage details: " + e.getMessage());
	                        }
	                    }
	                }
	                displayMenu(baggageCheckinServicePublish);
	                break;
	            case 5:
	                try {
	                	baggageCheckinServicePublish.updateBaggageStatusDetails();
	                } catch (Exception e) {
	                    System.out.println("Error updating baggage status: " + e.getMessage());
	                }
	                
	                
	                    while (loopThrough.equals("y")) {
	                        System.out.print("\n\nDo you want to Update Another Baggage Status (y/n) ? ");
	                        loopThrough = scn.nextLine().trim();
	                        if (loopThrough.equals("y")) {
	                            baggageCheckinServicePublish.updateBaggageStatusDetails();
	                        }
	                    }
	               
	                displayMenu(baggageCheckinServicePublish);
	                break;
	            case 6:
	            	try {
	                	baggageCheckinServicePublish.getAllMissingBaggeDetails();
	                } catch (Exception e) {
	                    System.out.println("Error updating baggage status: " + e.getMessage());
	                }
	                
	                
	                    while (loopThrough.equals("y")) {
	                        System.out.print("\n\nDo you want to Update Another Baggage Status (y/n) ? ");
	                        loopThrough = scn.nextLine().trim();
	                        if (loopThrough.equals("y")) {
	                            baggageCheckinServicePublish.updateBaggageStatusDetails();
	                        }
	                    }
	               
	                displayMenu(baggageCheckinServicePublish);
	                break;
	            case 7:
	            	System.out.println("Exited From the program.");
	            	System.exit(0);
	            	break;
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
