package com.guilherme.travelguide.controllers;
import com.guilherme.travelguide.dao.HotelDao;
import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Hotel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class HotelController {

    private final HotelDao hotelDao;
    City city = new City();

    public HotelController(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @GetMapping("/city/name/{cityName}/hotel/{hotelId}")
    public Hotel getHotelById(@PathVariable int hotelId) {
        return hotelDao.getHotelById(hotelId);
    }

    @GetMapping("/city/name/{cityName}/hotels")
    public List<Hotel> getHotelsByCityName(@PathVariable String cityName) {
        List<Hotel> hotels = hotelDao.getHotelsByCityName(cityName);
        return hotels;
    }
    @GetMapping("/city/name/{cityName}/hotel/picture/{hotelId}")
    public String getHotelPictureById(@PathVariable int hotelId) {
        return hotelDao.getHotelPictureById(hotelId);
    }
    @GetMapping("/city/name/{cityName}/hotel/website/{hotelId}")
    public String getHotelWebsiteById(@PathVariable int hotelId) {
        return hotelDao.getHotelWebsiteById(hotelId);
    }
    @GetMapping("/city/name/{cityName}/hotel/address/{hotelId}")
    public String getHotelAddressById(@PathVariable int hotelId) {
        return hotelDao.getHotelAddressById(hotelId);
    }
    @GetMapping("/city/name/{cityName}/hotel/phone/{hotelId}")
    public String getHotelPhoneById(@PathVariable int hotelId) {
        return hotelDao.getHotelPhoneById(hotelId);
    }
    @GetMapping("/city/name/{cityName}/hotel/price/{hotelId}")
    public String getHotelPriceById(@PathVariable int hotelId) {
        return hotelDao.getHotelPriceById(hotelId);
    }

}