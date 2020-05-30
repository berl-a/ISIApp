package com.berla.pwrapps.isiapp.dto

import lombok.Data

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;

    String getUsername() {
        return username
    }

    String getPassword() {
        return password
    }
}