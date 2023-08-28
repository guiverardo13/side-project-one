package com.guilherme.travelguide.security.controller;

import javax.validation.Valid;

import com.guilherme.travelguide.dao.UserDao;
import com.guilherme.travelguide.model.User;
import com.guilherme.travelguide.security.exception.DaoException;
import com.guilherme.travelguide.security.jwt.JWTFilter;
import com.guilherme.travelguide.security.jwt.TokenProvider;
import com.guilherme.travelguide.security.model.LoginDto;
import com.guilherme.travelguide.security.model.LoginResponseDto;
import com.guilherme.travelguide.security.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);

        User user;
        try {
            user = userDao.getUserByUsername(loginDto.getUsername());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            User user = userDao.createUser(newUser);

            if (user == null) {
                return ResponseEntity.badRequest().body("User registration failed.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (DataIntegrityViolationException e) {
            // Handle data integrity violation exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or email already exists.");
        } catch (DaoException e) {
            // Handle other DAO exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed.");
        }
    }
}


