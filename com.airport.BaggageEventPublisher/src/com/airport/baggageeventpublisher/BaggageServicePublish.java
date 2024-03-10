package com.airport.baggageeventpublisher;

public interface BaggageServicePublish {
	public void addBaggage();
	public void getBaggageDetailsById();
	public void updateBaggageDetails();
	public void deleteBaggageDetails();
	public void updateBaggageStatusDetails();
	public void getBaggageStatusByPassengerID();
	public void informMissingBaggage();
	public void getAllMissingBaggeDetails();
}
