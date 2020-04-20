package com.berla.pwrapps.isiapp.service

import com.berla.pwrapps.isiapp.model.User


public interface UserService {

    User register(User user, String[] roleNames);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}