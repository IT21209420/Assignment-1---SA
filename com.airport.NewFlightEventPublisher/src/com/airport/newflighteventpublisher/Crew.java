package com.airport.newflighteventpublisher;

public class Crew {
    private int id;
    private String name;
    private String position;
    private int flightId;

    public Crew() {
    }

    public Crew(int id, String name, String position, int flightId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.flightId = flightId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Crew [id=" + id + ", name=" + name + ", position=" + position + ", flightId=" + flightId + "]";
    }
}
