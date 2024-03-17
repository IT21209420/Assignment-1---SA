package com.airport.passangereventpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;



public class Activator implements BundleActivator {

	private ServiceRegistration publicServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Passenger Event Management Service Publisher Start and Running!");
		PassengerEvent publisherService = new PassengerEventImpl();
		publicServiceRegistration = context.registerService(
				PassengerEvent.class.getName(), publisherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Passenger Event Management Service Publisher Stop!");
		publicServiceRegistration.unregister();
	}

}
