package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.*;
import com.guilherme.travelguide.model.*;
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
    private final BarDao barDao;
    private final RestaurantDao restaurantDao;
    private final EventDao eventDao;
    private final TourDao tourDao;

    public LikeController(LikeDao likeDao, UserDao userDao, HotelDao hotelDao, BarDao barDao, RestaurantDao restaurantDao, EventDao eventDao, TourDao tourDao) {
        this.likeDao = likeDao;
        this.userDao = userDao;
        this.hotelDao = hotelDao;
        this.barDao = barDao;
        this.restaurantDao = restaurantDao;
        this.eventDao = eventDao;
        this.tourDao = tourDao;
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
        }

        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }

        // Determine the type of liked item based on the presence of specific properties
        if (like.getLikeHotelId() != 0) {
            Hotel hotel = hotelDao.getHotelById(like.getLikeHotelId());
            if (hotel == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel Not Found");
            }
            // Add the hotel to the user's liked list
            Like createdHotelLike = likeDao.addHotelToLikeList(user, like, hotel);
            return new ResponseEntity<>(createdHotelLike, HttpStatus.CREATED);
        } else if (like.getLikeBarId() != 0) {
            Bar bar = barDao.getBarById(like.getLikeBarId());
            if (bar == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bar Not Found");
            }
            // Add the bar to the user's liked list
            Like createdBarLike = likeDao.addBarToLikeList(user, like, bar);
            return new ResponseEntity<>(createdBarLike, HttpStatus.CREATED);
        } else if (like.getLikeRestaurantId() != 0) {
            Restaurant restaurant = restaurantDao.getRestaurantById(like.getLikeRestaurantId());
            if (restaurant == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found");
            }
            // Add the restaurant to the user's liked list
            Like createdRestaurantLike = likeDao.addRestaurantToLikeList(user, like, restaurant);
            return new ResponseEntity<>(createdRestaurantLike, HttpStatus.CREATED);
        } else if (like.getLikeEventId() != 0) {
            Event event = eventDao.getEventById(like.getLikeEventId());
            if (event == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
            }
            Like createdEventLike = likeDao.addEventToLikeList(user, like, event);
            return new ResponseEntity<>(createdEventLike, HttpStatus.CREATED);
        } else if (like.getLikeTourId() != 0) {
            Tour tour = tourDao.getTourById(like.getLikeTourId());
            if (tour == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
            }
            Like createdTourLike = likeDao.addTourToLikeList(user, like, tour);
            return new ResponseEntity<>(createdTourLike, HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Like Type");
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



