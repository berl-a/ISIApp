package com.berla.pwrapps.isiapp.dto;

import com.berla.pwrapps.isiapp.model.Car;
import com.berla.pwrapps.isiapp.model.Client;
import com.berla.pwrapps.isiapp.model.Driver;
import lombok.Data;

@Data
public class RideDtoFull extends RideDtoWithoutLinks {

    Driver driver;
    Car car;
    Client client;
}
