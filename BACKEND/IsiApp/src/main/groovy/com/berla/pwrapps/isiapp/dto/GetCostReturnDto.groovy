package com.berla.pwrapps.isiapp.dto

class GetCostReturnDto {

    GetCostReturnDto() {

    }

    GetCostReturnDto(String distance, String duration, String status) {
        this.distance = distance
        this.duration = duration
        this.status = status
    }
    private String distance;
    private String duration;
    private String status;

    private double price;

    String getDistance() {
        return distance
    }

    void setDistance(String distance) {
        this.distance = distance
    }

    String getDuration() {
        return duration
    }

    void setDuration(String duration) {
        this.duration = duration
    }

    String getStatus() {
        return status
    }

    void setStatus(String status) {
        this.status = status
    }

    double getPrice() {
        return price
    }

    void setPrice(double price) {
        this.price = price
    }
}
