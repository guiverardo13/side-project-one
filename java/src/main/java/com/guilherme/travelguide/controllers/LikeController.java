package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.LikeDao;
import com.guilherme.travelguide.dao.UserDao;
import com.guilherme.travelguide.model.Hotel;
import com.guilherme.travelguide.model.Like;
import com.guilherme.travelguide.model.User;
import com.guilherme.travelguide.security.exception.DaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin
@RestController
public class LikeController {

    private final LikeDao likeDao;
    private final UserDao userDao;

    public LikeController(LikeDao likeDao, UserDao userDao) {
        this.likeDao = likeDao;
        this.userDao = userDao;
    }


    @GetMapping("/likes/{userId}")
    public List<Like> getLikesByUsername (@PathVariable int userId) {
        return likeDao.getLikesByUserId(userId);
    }

    @GetMapping("/like/{likeId}")
    public Like getLikeById (@PathVariable int likeId) {
        return likeDao.getLikeById(likeId);
    }

    @GetMapping("/like/hotel/{hotelId}")
    public Hotel getHotelById (@PathVariable int hotelId) {
        return likeDao.getHotelById(hotelId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/likes/add", method = RequestMethod.POST)
    public ResponseEntity<Like> addLike(@RequestBody Like like, @RequestParam Integer userId) {
        if (like == null || userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Like or User ID");
        } else {
            User user = userDao.getUserById(userId); // Replace userDao with your actual user data access object
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
            }

            Like createdLike = likeDao.addHotelToLikeList(user, like); // Pass the user object to the DAO method
            return new ResponseEntity<>(createdLike, HttpStatus.CREATED);
        }
    }
//
//    @PostMapping("/likes/add/like/{likeId}/user/{userId}")
//    public ResponseEntity<String> addUserLike(@PathVariable int userId, @PathVariable int likeId) {
//        try {
//            boolean added = likeDao.addUserLike(userId, likeId);
//            if (added) {
//                return ResponseEntity.ok("Like added successfully");
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add like");
//            }
//        } catch (DaoException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

}



