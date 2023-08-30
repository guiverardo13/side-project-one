package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Bar;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface BarDao {

    List<Bar> getBarsByCityName(String cityName);
    Bar getBarById(int barId);
    String getBarPictureById(int barId);
    String getBarWebsiteById(int barId);
    String getBarAddressById(int barId);
    String getBarPhoneById(int barId);
    String getBarPriceById(int barId);
}
