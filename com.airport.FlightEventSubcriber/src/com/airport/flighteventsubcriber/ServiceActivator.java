package com.airport.flighteventsubcriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.airport.newflighteventpublisher.NewFlightEventPublish;
 

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(NewFlightEventPublish.class.getName());

		int choice = 0;
		while(choice == -1) {
			switch(choice) {
			case 1:NewFlightEventPublish servicePub1ish = (NewFlightEventPublish) context.getService (serviceReference) ;
				   System.out.println (servicePub1ish.publishNewFlightEvent());
			case 2:System.out.println("Choose 1");
			}
		}
			
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye !!!");
		context.ungetService(serviceReference);
		
	}

}
