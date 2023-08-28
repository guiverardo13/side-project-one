package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.User;
import com.guilherme.travelguide.security.model.RegisterUserDto;

public interface UserDao {
    User getUserById(int id); // /user/{id}
    User getUserByUsername(String username); // /user/{username}
    User createUser(RegisterUserDto user); // /user POST
}
