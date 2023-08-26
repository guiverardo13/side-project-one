package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.CityDao;
import com.guilherme.travelguide.model.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CityController {

    private final CityDao cityDao;

    public CityController(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @RequestMapping(path = "/city/{cityId}", method = RequestMethod.GET)
    public City getCityById(@PathVariable int cityId) {
        return cityDao.getCityById(cityId);
    }

    @RequestMapping(path = "/city", method = RequestMethod.GET)
    public List<City> getCities() {
        return cityDao.getCities();
    }

    @RequestMapping(path = "/city/name/{cityName}", method = RequestMethod.GET)
    public City getCityByName(@PathVariable String cityName) {
        return cityDao.getCityByName(cityName);
    }

    @GetMapping("/city/name/{cityName}/cover-picture")
    public ResponseEntity<String> getCoverPictureByCityName(@PathVariable String cityName) {
        System.out.println("Trying to access cover for city: " + cityName);
        String coverPictureUrl = cityDao.getCoverPictureByCityName(cityName);

        if (coverPictureUrl != null) {
            return ResponseEntity.ok(coverPictureUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/city/name/{cityName}/description")
    public ResponseEntity<String> getDescriptionByCityName(@PathVariable String cityName) {
        System.out.println("Trying to access cover for city: " + cityName);
        String cityDescription = cityDao.getDescriptionByCityName(cityName);

        if (cityDescription != null) {
            return ResponseEntity.ok(cityDescription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/city/name/{cityName}/video")
    public ResponseEntity<String> getVideoByCityName(@PathVariable String cityName) {
        System.out.println("Trying to access cover for city: " + cityName);
        String cityVideo = cityDao.getVideoByCityName(cityName);

        if (cityVideo != null) {
            return ResponseEntity.ok(cityVideo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
