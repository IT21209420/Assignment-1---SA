package com.airport.flighteventmanagersubscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.newflighteventpublisher.NewFlightEventPublish;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

    public void start(BundleContext context) throws Exception {
        System.out.println("Start Subscriber Service");
        serviceReference = context.getServiceReference(NewFlightEventPublish.class.getName());

            NewFlightEventPublish newFlightEventPublish = (NewFlightEventPublish) context.getService(serviceReference);
            
            displayMenu(newFlightEventPublish);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Good Bye!!!");
        context.ungetService(serviceReference);
    }

    private void displayMenu(NewFlightEventPublish newFlightEventPublish) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n\n---------- Flight Event Subscriber Menu ----------");
            System.out.println("1 - Publish New Flight Event");
            System.out.println("2 - Search Flight Event");
            System.out.println("3 - Update Flight Event");
            System.out.println("4 - Delete Flight Event");
            System.out.println("5 - View All Flight Events");
            System.out.println("6 - Assign Crew to Flight Events");
            System.out.println("7 - View All Crew Members in a Flight");
            System.out.println("8 - Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        newFlightEventPublish.publishNewFlightEvent();
                        break;
                    case 2:
                        newFlightEventPublish.searchFlightEvent();
                        break;
                    case 3:
                        newFlightEventPublish.updateFlightEvent();
                        break;
                    case 4:
                        newFlightEventPublish.deleteFlightEvent();
                        break;
                    case 5:
                        newFlightEventPublish.getAllFlightEvents();
                        break;
                    case 6:
                        newFlightEventPublish.assignCrewToFlight();
                        break;
                    case 7:
                        newFlightEventPublish.getAllCrew();
                        break;
                        
                    case 8:
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
