package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.UserDao;
import com.guilherme.travelguide.model.User;
import com.guilherme.travelguide.security.model.RegisterUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin

@RestController
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        return userDao.getUserById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody RegisterUserDto user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found :(");
        } else {

            User createdUser = userDao.createUser(user);

            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
    }


}
