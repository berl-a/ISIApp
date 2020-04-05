package com.berla.pwrapps.isiapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Driver extends BaseEntity {

    private String firstName;
    private String lastName;
}
