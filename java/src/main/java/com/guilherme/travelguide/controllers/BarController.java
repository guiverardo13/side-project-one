package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.BarDao;
import com.guilherme.travelguide.model.Bar;
import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Hotel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class BarController {

    private final BarDao barDao;

    City city = new City();

    public BarController(BarDao barDao) {
        this.barDao = barDao;
    }

    @GetMapping("/city/name/{cityName}/bar/{barId}")
    public Bar getBarById(@PathVariable int barId) {
        return barDao.getBarById(barId);
    }
    @GetMapping("/city/name/{cityName}/bars")
    public List<Bar> getBarsByCityName(@PathVariable String cityName) {
        List<Bar> bars = barDao.getBarsByCityName(cityName);
        return bars;
    }
    @GetMapping("/city/name/{cityName}/bar/picture/{barId}")
    public String getBarPictureById(@PathVariable int barId) {
        return barDao.getBarPictureById(barId);
    }
    @GetMapping("/city/name/{cityName}/bar/website/{barId}")
    public String getBarWebsiteById(@PathVariable int barId) {
        return barDao.getBarWebsiteById(barId);
    }
    @GetMapping("/city/name/{cityName}/bar/address/{barId}")
    public String getBarAddressById(@PathVariable int barId) {
        return barDao.getBarAddressById(barId);
    }
    @GetMapping("/city/name/{cityName}/bar/phone/{barId}")
    public String getBarPhoneById(@PathVariable int barId) {
        return barDao.getBarPhoneById(barId);
    }
    @GetMapping("/city/name/{cityName}/bar/price/{barId}")
    public String getBarPriceById(@PathVariable int barId) {
        return barDao.getBarPriceById(barId);
    }


}
