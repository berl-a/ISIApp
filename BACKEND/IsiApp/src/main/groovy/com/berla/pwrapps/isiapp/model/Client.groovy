package com.berla.pwrapps.isiapp.model

import com.berla.pwrapps.isiapp.dto.ClientDto
import lombok.Data

import javax.persistence.*

@Entity
@Table(name = "clients")
@Data
public class Client extends BaseEntity {

    private String googleId;
    private String firstName;
    private String lastName;

    /**
     * Constructor to create Client object from ClientDto object
     * @param clientDto ClientDto object
     */
    public Client(ClientDto clientDto) {
        firstName = clientDto.getFirstName();
        lastName = clientDto.getLastName();
    }

    /**
     * Get ClientDto for the current object
     * @return ClientDto containing information from current object
     */
    public ClientDto toClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(getFirstName());
        clientDto.setLastName(getLastName());
        return clientDto;
    }

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
