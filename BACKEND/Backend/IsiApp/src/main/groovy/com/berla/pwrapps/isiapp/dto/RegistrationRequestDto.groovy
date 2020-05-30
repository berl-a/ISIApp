package com.berla.pwrapps.isiapp.dto

import lombok.Data

@Data
public class RegistrationRequestDto {

    private String username;
    private String password;
    private String[] roles;

    String getUsername() {
        return username
    }

    String getPassword() {
        return password
    }

    String[] getRoles() {
        return roles
    }
}