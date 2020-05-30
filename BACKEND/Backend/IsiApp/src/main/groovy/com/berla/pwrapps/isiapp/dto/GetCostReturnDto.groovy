package com.berla.pwrapps.isiapp.dto

class GetCostReturnDto {

    GetCostReturnDto(double price) {
        this.price = price
    }
    private double price;

    double getPrice() {
        return price
    }

    void setPrice(double price) {
        this.price = price
    }
}
