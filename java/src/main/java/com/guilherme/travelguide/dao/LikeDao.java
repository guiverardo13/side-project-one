package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface LikeDao {

    List<Like> getLikesByUserId(int userId);

    Like getLikeById(int likeId);

    Like addHotelToLikeList(User user, Like like, Hotel hotel);

    Like addBarToLikeList(User user, Like like, Bar bar);
    Like addRestaurantToLikeList(User user, Like like, Restaurant restaurant);
    Like addEventToLikeList(User user, Like like, Event event);
    Like addTourToLikeList(User user, Like like, Tour tour);

    boolean deleteLike(int likeId, int userId);




}
