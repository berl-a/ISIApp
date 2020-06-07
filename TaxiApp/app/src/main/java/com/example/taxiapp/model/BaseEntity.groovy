package com.example.taxiapp.model


class BaseEntity {

    private Long id;

    private Date created;

    private Date updated;

    private Status status;

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }
}
