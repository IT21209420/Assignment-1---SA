package com.airport.employeeeventpublisher;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	
	ServiceRegistration publicServiceRegistration;

	
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Employee Event Publisher service");
		EmployeeEventPublish publisherService = new EmployeeEventPublishImpl();
		publicServiceRegistration = context. registerService(
		EmployeeEventPublish.class.getName(), publisherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Employee Event Publisher service");
		publicServiceRegistration.unregister();
	}

}

