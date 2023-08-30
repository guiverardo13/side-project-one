package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.BarDao;
import com.guilherme.travelguide.dao.RestaurantDao;
import com.guilherme.travelguide.model.Bar;
import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Restaurant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    private final RestaurantDao restaurantDao;

    City city = new City();

    public RestaurantController(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @GetMapping("/city/name/{cityName}/restaurant/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable int restaurantId) {
        return restaurantDao.getRestaurantById(restaurantId);
    }
    @GetMapping("/city/name/{cityName}/restaurants")
    public List<Restaurant> getRestaurantsByCityName(@PathVariable String cityName) {
        List<Restaurant> restaurants = restaurantDao.getRestaurantsByCityName(cityName);
        return restaurants;
    }
    @GetMapping("/city/name/{cityName}/restaurant/picture/{restaurantId}")
    public String getRestaurantPictureById(@PathVariable int restaurantId) {
        return restaurantDao.getRestaurantPictureById(restaurantId);
    }
    @GetMapping("/city/name/{cityName}/restaurant/website/{restaurantId}")
    public String getRestaurantWebsiteById(@PathVariable int restaurantId) {
        return restaurantDao.getRestaurantWebsiteById(restaurantId);
    }
    @GetMapping("/city/name/{cityName}/restaurant/address/{restaurantId}")
    public String getRestaurantAddressById(@PathVariable int restaurantId) {
        return restaurantDao.getRestaurantAddressById(restaurantId);
    }
    @GetMapping("/city/name/{cityName}/restaurant/phone/{restaurantId}")
    public String getRestaurantPhoneById(@PathVariable int restaurantId) {
        return restaurantDao.getRestaurantPhoneById(restaurantId);
    }
    @GetMapping("/city/name/{cityName}/restaurant/price/{restaurantId}")
    public String getRestaurantPriceById(@PathVariable int restaurantId) {
        return restaurantDao.getRestaurantPriceById(restaurantId);
    }
}
