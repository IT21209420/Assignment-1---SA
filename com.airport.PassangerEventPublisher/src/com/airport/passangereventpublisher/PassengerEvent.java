package com.airport.passangereventpublisher;

public interface PassengerEvent {
	public void bookPassenger();
	public void searchPassenger();
	public void updatePassenger();
	public void getAllPassengers();
	public void deletePassenger();
	public void assignSeat();
	public void getSeatAssignment();
	public void updateSeatNumber();
}
