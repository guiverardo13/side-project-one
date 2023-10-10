package com.guilherme.travelguide.controllers;
import com.guilherme.travelguide.dao.TourDao;
import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Tour;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TourController {

    private final TourDao tourDao;
    City city = new City();

    public TourController(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @GetMapping("/city/name/{cityName}/pois/{tourId}")
    public Tour getTourlById(@PathVariable int tourId) {
        return tourDao.getTourById(tourId);
    }

    @GetMapping("/city/name/{cityName}/pois")
    public List<Tour> getToursByCityName(@PathVariable String cityName) {
        List<Tour> tours = tourDao.getToursByCityName(cityName);
        return tours;
    }

    @GetMapping("/city/name/{cityName}/poi/picture/{tourId}")
    public String getTourPictureById(@PathVariable int tourId) {
        return tourDao.getTourPictureById(tourId);
    }

    @GetMapping("/city/name/{cityName}/poi/website/{tourId}")
    public String getTourWebsiteById(@PathVariable int tourId) {
        return tourDao.getTourWebsiteById(tourId);
    }

    @GetMapping("/city/name/{cityName}/poi/address/{tourId}")
    public String getTourAddressById(@PathVariable int tourId) {
        return tourDao.getTourAddressById(tourId);
    }

    @GetMapping("/city/name/{cityName}/poi/phone/{tourId}")
    public String getTourPhoneById(@PathVariable int tourId) {
        return tourDao.getTourPhoneById(tourId);
    }

    @GetMapping("/city/name/{cityName}/poi/price/{tourId}")
    public String getTourPriceById(@PathVariable int tourId) {
        return tourDao.getTourPriceById(tourId);
    }

}
