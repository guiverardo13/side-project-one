package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Hotel;
import com.guilherme.travelguide.model.Like;
import com.guilherme.travelguide.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface LikeDao {

    List<Like> getLikesByUserId(int userId);

    Like getLikeById(int likeId);

    Like addHotelToLikeList(User user, Like like, Hotel hotel);

    boolean deleteLike(int likeId, int userId);




}
