package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.HotelDao;
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
    private final HotelDao hotelDao;

    public LikeController(LikeDao likeDao, UserDao userDao, HotelDao hotelDao) {
        this.likeDao = likeDao;
        this.userDao = userDao;
        this.hotelDao = hotelDao;
    }

    @GetMapping("/likes/{userId}")
    public List<Like> getLikesByUsername (@PathVariable int userId) {
        return likeDao.getLikesByUserId(userId);
    }

    @GetMapping("/like/{likeId}")
    public Like getLikeById (@PathVariable int likeId) {
        return likeDao.getLikeById(likeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/likes/add/user/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Like> addLike(@RequestBody Like like, @PathVariable Integer userId) {
        if (like == null || userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Like or User ID");
        } else {
            User user = userDao.getUserById(userId); // Replace userDao with your actual user data access object
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
            }

            // Fetch the hotel information by its ID or any other identifier.
            // You may need to adjust this based on how you fetch hotel details in your application.
            Hotel hotel = hotelDao.getHotelById(like.getLikeHotelId());

            // Check if the hotel exists
            if (hotel == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel Not Found");
            }

            // Pass the hotel object to the DAO method
            Like createdLike = likeDao.addHotelToLikeList(user, like, hotel);

            return new ResponseEntity<>(createdLike, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/like/delete/{likeId}/user/{userId}")
    public ResponseEntity<String> deleteLike(@PathVariable Integer likeId, @PathVariable Integer userId) {
        try {

            boolean isDeleted = likeDao.deleteLike(likeId, userId);

            if (isDeleted) {
                return ResponseEntity.ok("Like deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like not found or does not belong to the user");
            }
        } catch (DaoException e) {
            // Handle any exceptions or errors that occur during the deletion
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete like: " + e.getMessage());
        }
    }


}



