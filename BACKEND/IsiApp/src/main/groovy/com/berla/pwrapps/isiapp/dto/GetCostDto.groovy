package com.berla.pwrapps.isiapp.dto

import lombok.Data

@Data
class GetCostDto {

    private String origin;
    private String destination;

    String getOrigin() {
        return origin
    }

    void setOrigin(String origin) {
        this.origin = origin
    }

    String getDestination() {
        return destination
    }

    void setDestination(String destination) {
        this.destination = destination
    }

}
