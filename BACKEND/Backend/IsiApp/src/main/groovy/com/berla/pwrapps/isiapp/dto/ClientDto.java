package com.berla.pwrapps.isiapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Data transfer object for user
 */
@Data
public class ClientDto {

    @NotNull
    private String googleId;
    private String firstName;
    private String lastName;
}
