package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Event;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface EventDao {

    List<Event> getEventsByCityName(String cityName);
    Event getEventById(int eventId);
    String getEventPictureById(int eventId);
    String getEventWebsiteById(int eventId);
    String getEventAddressById(int eventId);
    String getEventPhoneById(int eventId);
    String getEventPriceById(int eventId);

}
