package com.airport.baggageeventpublisher;

public class Baggage {
	private int BaggageID;
	private int PassengerID;
	private int FlightNUmber;
	private float Weight;
	private String Status;
	public int getBaggageID() {
		return BaggageID;
	}
	public void setBaggageID(int baggageID) {
		BaggageID = baggageID;
	}
	public int getPassengerID() {
		return PassengerID;
	}
	public void setPassengerID(int passengerID) {
		PassengerID = passengerID;
	}
	public int getFlightNUmber() {
		return FlightNUmber;
	}
	public void setFlightNUmber(int flightNUmber) {
		FlightNUmber = flightNUmber;
	}
	public float getWeight() {
		return Weight;
	}
	public void setWeight(float weight) {
		Weight = weight;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
