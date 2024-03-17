package com.airport.baggageeventpublisher;

public class Baggage {
	@Override
	public String toString() {
		return "Baggage [BaggageID=" + BaggageID + ", PassengerID=" + PassengerID + ", FlightNUmber=" + FlightNumber
				+ ", Weight=" + Weight + ", Status=" + Status + "]";
	}
	private int BaggageID;
	private int PassengerID;
	private int FlightNumber;
	private double Weight;
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
	public int getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNUmber(int flightNUmber) {
		FlightNumber = flightNUmber;
	}
	public double getWeight() {
		return Weight;
	}
	public void setWeight(double weight) {
		Weight = weight;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
