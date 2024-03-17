package com.airport.employeemanagersubcriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.employeeeventpublisher.EmployeeEventPublish;

import java.util.Scanner;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Employee Manager Subscriber Service");
		serviceReference = context.getServiceReference(EmployeeEventPublish.class.getName());
		EmployeeEventPublish EmployeeEventPublish = (EmployeeEventPublish) context.getService (serviceReference) ;
		
		displayMenu(EmployeeEventPublish);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Employee Manager subscriber service");
		context.ungetService(serviceReference);
		
	}
	public void displayMenu(EmployeeEventPublish employeeEventPublish) {
		
		int option;	
		Scanner itemFunctions = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("---------- Employe Management - Employee Manager ----------");
		System.out.println("1 - Add a new Employee");
		System.out.println("2 - Get Employee details using NIC");
		System.out.println("3 - Get all Employee details");
		System.out.println("4 - Delete an employee");
		System.out.println("5 - Calculate employee total salary");
		System.out.println("6 - Exit");
		System.out.print("\nSelect an option : ");
		
		option = Integer.parseInt(itemFunctions.nextLine().trim());
		
		switch(option) {
			case 1:
				employeeEventPublish.createEmployee();
				displayMenu(employeeEventPublish);
				break;
			case 2:
				employeeEventPublish.getEmployee();
				displayMenu(employeeEventPublish);
				break;
			case 3:
				employeeEventPublish.getAllEmployeeDetails();
				displayMenu(employeeEventPublish);
				break;
			case 4:
				employeeEventPublish.deleteEmployee();
				displayMenu(employeeEventPublish);
				break;
			case 5:
				employeeEventPublish.calculateTotalSalary();
				displayMenu(employeeEventPublish);
				break;
			case 6:
				System.out.println("System Exit.");
				option = 0;
				break;
			default:
				System.out.println("Incorrect Input. Please Enter Valid Number...");
				displayMenu(employeeEventPublish);
		}
		
		
	}


}
