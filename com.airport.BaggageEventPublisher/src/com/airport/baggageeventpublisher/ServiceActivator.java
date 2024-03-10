package com.airport.baggageeventpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	
	ServiceRegistration publicServiceRegistration;

	
	public void start(BundleContext context) throws Exception {
		
		BaggageServicePublish publisherService = new BaggageServicePublishImpl();
		publicServiceRegistration = context. registerService(
	    BaggageServicePublish.class.getName(), publisherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Baggage Checkin Service Publisher Stop");
		publicServiceRegistration.unregister();
	}

}

