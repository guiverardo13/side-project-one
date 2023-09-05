package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Like;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikeDao {

    List<Like> getLikesByUsername(String username);
    String getCityNameByLikeCityId (int likeCityId, String username);
    Like getHotelByLikeHotelId(int likeHotelId, String username);
    Like getBarByLikeBarId(int likeBarId, String username);
    Like getRestaurantByLikeRestaurantId(int likeRestaurantId, String username);
//    Like getEventByLikeEventId(int likeEventId, String username);
//    Like getTourByLikeTourId(int likeTourId, String username);




}
