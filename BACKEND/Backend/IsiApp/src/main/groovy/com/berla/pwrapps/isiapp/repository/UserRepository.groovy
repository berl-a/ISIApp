package com.berla.pwrapps.isiapp.repository

import com.berla.pwrapps.isiapp.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    Optional<User> findById(Long id);
}