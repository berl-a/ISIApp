package com.example.taxiapp.Utils.taxiapp.RequestBody;

public class CostData {


    private String origin;

    public CostData(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    private String destination;

    String getOrigin() {
        return origin;
    }

    void setOrigin(String origin) {
        this.origin = origin;
    }

    String getDestination() {
        return destination;
    }

    void setDestination(String destination) {
        this.destination = destination;
    }

}
