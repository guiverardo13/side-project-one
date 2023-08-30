package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RestaurantDao {

    List<Restaurant> getRestaurantsByCityName(String cityName);
    Restaurant getRestaurantById(int restaurantId);
    String getRestaurantPictureById(int restaurantId);
    String getRestaurantWebsiteById(int restaurantId);
    String getRestaurantAddressById(int restaurantId);
    String getRestaurantPhoneById(int restaurantId);
    String getRestaurantPriceById(int restaurantId);

}
