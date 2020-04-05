package com.berla.pwrapps.isiapp.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Car extends BaseEntity {

    private String brand;
    private String model;
}
