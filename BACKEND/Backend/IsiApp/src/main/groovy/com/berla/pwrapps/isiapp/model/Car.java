package com.berla.pwrapps.isiapp.model;

import com.berla.pwrapps.isiapp.dto.CarDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car extends BaseEntity {

    private String brand;
    private String model;

    @OneToMany(mappedBy="car")
    private List<Ride> rides;

    @OneToOne(mappedBy = "car")
    private Driver driver;

    public Car() {
    }

    /**
     * Constructor to create Car object from CarDto object
     * @param carDto CarDto object
     */
    public Car(CarDto carDto) {
        brand = carDto.getBrand();
        model = carDto.getModel();
    }

    /**
     * Get CarDto for the current object
     * @return CarDto containing information from current object
     */
    public CarDto toCarDto() {
        CarDto carDto = new CarDto();
        carDto.setBrand(brand);
        carDto.setModel(model);
        return carDto;
    }

    @JsonIgnore
    public Driver getDriver() {
        return driver;
    }

    @JsonIgnore
    public List<Ride> getRides() {
        return rides;
    }

    @Override
    public String toString() {
        return "Car{}";
    }
}
