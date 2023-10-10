package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Tour;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TourDao {

    List<Tour> getToursByCityName(String cityName);
    Tour getTourById(int tourId);
    String getTourPictureById(int tourId);
    String getTourWebsiteById(int tourId);
    String getTourAddressById(int tourId);
    String getTourPhoneById(int tourId);
    String getTourPriceById(int tourId);

}
