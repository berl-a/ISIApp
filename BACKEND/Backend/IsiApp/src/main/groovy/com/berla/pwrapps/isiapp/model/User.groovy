package com.berla.pwrapps.isiapp.model

import lombok.Data

import javax.persistence.*

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    String getUsername() {
        return username
    }

    String getPassword() {
        return password
    }

    List<Role> getRoles() {
        return roles
    }

    Status getStatus() {
        return super.getStatus();
    }

    void setUsername(String username) {
        this.username = username
    }

    void setPassword(String password) {
        this.password = password
    }

    void setRoles(List<Role> roles) {
        this.roles = roles
    }

    void setStatus(Status status) {
        super.setStatus(status);
    }

}
