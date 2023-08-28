package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Hotel;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface HotelDao {

    List<Hotel> getHotelsByCityName(String cityName);
    Hotel getHotelById(int hotelId);
    String getHotelPictureById(int hotelId);
    String getHotelWebsiteById(int hotelId);
    String getHotelAddressById(int hotelId);
    String getHotelPhoneById(int hotelId);
    String getHotelPriceById(int hotelId);

}
