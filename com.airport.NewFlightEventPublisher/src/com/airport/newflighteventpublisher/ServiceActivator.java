package com.airport.newflighteventpublisher;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	
	ServiceRegistration publicServiceRegistration;

	
	public void start(BundleContext context) throws Exception {
		System.out.println("Baggage Checkin Service Publisher Start");
		NewFlightEventPublish publisherService = new NewFlightEventPublishImpl();
		publicServiceRegistration = context.registerService(
		NewFlightEventPublish.class.getName(), publisherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Baggage Checkin Service Publisher Stop");
		publicServiceRegistration.unregister();
	}

}

