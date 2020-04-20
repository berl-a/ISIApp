package com.berla.pwrapps.isiapp.model

import lombok.Data
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

import javax.persistence.*

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;

    @Enumerated(EnumType.STRING)
    private Status status;

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Date getCreated() {
        return created
    }

    void setCreated(Date created) {
        this.created = created
    }

    Date getUpdated() {
        return updated
    }

    void setUpdated(Date updated) {
        this.updated = updated
    }

    Status getStatus() {
        return status
    }

    void setStatus(Status status) {
        this.status = status
    }
}
