package com.airport.databasePublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Database Publisher service started");
		Database database = new DatabaseImpl();
		serviceRegistration = context.registerService(Database.class.getName(), database, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Database Publisher service stop!");
		serviceRegistration.unregister();
	}

}
