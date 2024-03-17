package com.airport.employeesubcriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.employeeeventpublisher.EmployeeEventPublish;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Employee Subscriber Service");
		serviceReference = context.getServiceReference(EmployeeEventPublish.class.getName());
		EmployeeEventPublish EmployeeEventPublish = (EmployeeEventPublish) context.getService (serviceReference) ;
		
		displayMenu(EmployeeEventPublish);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Employee Subscriber Service");
		context.ungetService(serviceReference);
		
	}
	
public void displayMenu(EmployeeEventPublish employeeEventPublish) {
		
		int option;
		
		Scanner itemFunctions = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("---------- Employe Management - Employee ----------");
		System.out.println("1 - Get Employee details using NIC");
		System.out.println("2 - Get all Employee details");
		System.out.println("3 - Get total available leaves for the year");
		System.out.println("4 - Exit");
		System.out.print("\nSelect an option : ");
		
		option = Integer.parseInt(itemFunctions.nextLine().trim());
		
		switch(option) {
			case 1:
				employeeEventPublish.getEmployee();
				displayMenu(employeeEventPublish);
				break;
			case 2:
				employeeEventPublish.getAllEmployeeDetails();
				displayMenu(employeeEventPublish);
				break;
			case 3:
				employeeEventPublish.getAvailableEmployeeLeaves();
				displayMenu(employeeEventPublish);
				break;
			case 4:
				System.out.println("System Exit.");
				option = 0;
				break;
			default:
				System.out.println("Incorrect Input. Please Enter Valid Number...");
		}
	}

}
