package com.airport.passengerseatmanagersubscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.passangereventpublisher.PassengerEvent;

public class Activator implements BundleActivator {

    private ServiceReference serviceReference;

  

    public void start(BundleContext bundleContext) throws Exception {
       
        System.out.println("Passenger Seat Event Manager started.");

        serviceReference = bundleContext.getServiceReference(PassengerEvent.class.getName());
        PassengerEvent passengerEvent = (PassengerEvent) bundleContext.getService(serviceReference);

        displayMenu(passengerEvent);
    }

    public void stop(BundleContext bundleContext) throws Exception {
       
        bundleContext.ungetService(serviceReference);
        System.out.println("Passenger Seat Event Manager stopped.");
    }

    private void displayMenu(PassengerEvent passengerEvent) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n\n---------- Passenger Seat Manager Menu ----------");
            System.out.println("1 - Assign a seat");
            System.out.println("2 - View Seat Number of a Passenger");
            System.out.println("3 - Update Seat number of a Passenger");
            System.out.println("4 - View All Passengers");
            System.out.println("5 - Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        passengerEvent.assignSeat();
                        break;
                    case 2:
                        passengerEvent.getSeatAssignment();
                        break;
                    case 3:
                        passengerEvent.updateSeatNumber();
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
