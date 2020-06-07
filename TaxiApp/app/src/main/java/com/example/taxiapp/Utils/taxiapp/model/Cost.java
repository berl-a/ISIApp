package com.example.taxiapp.Utils.taxiapp.model;

public class Cost {

    private String distance;
    private String duration;
    private String status;

    private double price;

    public String getDistance() {
        return distance;
    }

    void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    void setDuration(String duration) {
        this.duration = duration;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }
}
