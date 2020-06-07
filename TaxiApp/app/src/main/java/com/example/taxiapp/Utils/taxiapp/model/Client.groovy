package com.example.taxiapp.Utils.taxiapp.model

public class Client {

    private String googleId;
    private String firstName;
    private String lastName;


    @Override
    Long getId() {
        return super.getId()
    }

    @Override
    void setId(Long id) {
        super.setId(id)
    }

    String getGoogleId() {
        return googleId
    }

    void setGoogleId(String googleId) {
        this.googleId = googleId
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }
}
