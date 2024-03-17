package com.airport.passengereventmanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.passangereventpublisher.PassengerEvent;

import java.util.Scanner;

public class Activator implements BundleActivator {

  
    private ServiceReference serviceReference;

    

    public void start(BundleContext bundleContext) throws Exception {
        
        

        serviceReference = bundleContext.getServiceReference(PassengerEvent.class.getName());
        PassengerEvent passengerEvent = (PassengerEvent) bundleContext.getService(serviceReference);

        System.out.println("Passenger Event Manager started.");
        displayMenu(passengerEvent);
    }

    public void stop(BundleContext bundleContext) throws Exception {
       
    	bundleContext.ungetService(serviceReference);
        System.out.println("Passenger Event Manager stopped.");
    }

    private void displayMenu(PassengerEvent passengerEvent) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n\n---------- Passenger Event Manager Menu ----------");
            System.out.println("1 - Book Passenger");
            System.out.println("2 - Search Passenger");
            System.out.println("3 - Update Passenger");
            System.out.println("4 - View All Passengers");
            System.out.println("5 - Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        passengerEvent.bookPassenger();
                        break;
                    case 2:
                        passengerEvent.searchPassenger();
                        break;
                    case 3:
                        passengerEvent.updatePassenger();
                        break;
                    case 4:
                        passengerEvent.getAllPassengers();
                        break;
                    case 5:
                        choice = 0; // Exit the loop
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
