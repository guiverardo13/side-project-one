package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.City;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CityDao {

    List<City> getCities();
    City getCityById(int cityId);
    City getCityByName(String cityName);
    String getCoverPictureByCityName(String cityName);
    String getDescriptionByCityName(String cityName);
    String getVideoByCityName(String cityName);

}
