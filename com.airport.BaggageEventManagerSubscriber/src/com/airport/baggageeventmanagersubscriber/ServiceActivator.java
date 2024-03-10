package com.airport.baggageeventmanagersubscriber;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.baggageeventpublisher.BaggageServicePublish;

import java.util.Scanner;

public class ServiceActivator implements BundleActivator {
//it21209420
	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
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
		
		Scanner itemFunctions = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("---------- Baggage Management  - Manager ----------");
		System.out.println("1  - Add Baggage Details");
		System.out.println("2  - Get all Items");
		System.out.println("3  - Search Item using Item ID");
		System.out.println("4  - Search Item using Item name");
		System.out.println("5  - Delete Item from the System");
		System.out.print("Select an option : ");
		
		option = Integer.parseInt(itemFunctions.nextLine().trim());
		
		switch(option) {
			case 1:
				baggageCheckinServicePublish.addBaggage();
				
				while(loopThrough.equals("y")) {
					System.out.println("\n\nDo you want to Add Another item (y/n) ? ");
					loopThrough = itemFunctions.nextLine().trim();
		
					if(loopThrough.equals("y")) {
//						baggageCheckinServicePublish.createItem();
					}
				}
				displayMenu(baggageCheckinServicePublish);
				break;
//			case 2:
//				itemInterface.displayAllItems();
//				displayItemMenu(itemInterface);
//				break;
//			case 3:
//				itemInterface.getItemDetailsById();
//				displayItemMenu(itemInterface);
//				break;
//			case 4:
//				itemInterface.getItemDetailsByName();
//				displayItemMenu(itemInterface);
//				break;
//			case 5:
//				itemInterface.deleteItem();
//				displayItemMenu(itemInterface);
//				break;
			default:
				System.out.println("Incorrect Input. Please Enter Valid Number...");
				displayMenu(baggageCheckinServicePublish);
		}
		
		
	}


}
