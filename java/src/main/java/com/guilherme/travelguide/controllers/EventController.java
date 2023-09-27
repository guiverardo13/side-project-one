package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.EventDao;
import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Event;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class EventController {

    private final EventDao eventDao;
    City city = new City();

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @GetMapping("/city/name/{cityName}/events")
    public List<Event> getEventsByCityName(@PathVariable String cityName) {
        List<Event> events = eventDao.getEventsByCityName(cityName);
        return events;
    }

    @GetMapping("/city/name/{cityName}/event/{eventId}")
    public Event getEventById(@PathVariable int eventId) {
        return eventDao.getEventById(eventId);
    }

    @GetMapping("/city/name/{cityName}/event/picture/{eventId}")
    public String getEventPictureById(@PathVariable int eventId) {
        return eventDao.getEventPictureById(eventId);
    }

    @GetMapping("/city/name/{cityName}/event/website/{eventId}")
    public String getEventWebsiteById(@PathVariable int eventId) {
        return eventDao.getEventWebsiteById(eventId);
    }

    @GetMapping("/city/name/{cityName}/event/address/{eventId}")
    public String getEventAddressById(@PathVariable int eventId) {
        return eventDao.getEventAddressById(eventId);
    }

    @GetMapping("/city/name/{cityName}/event/phone/{eventId}")
    public String getEventPhoneById(@PathVariable int eventId) {
        return eventDao.getEventPhoneById(eventId);
    }

    @GetMapping("/city/name/{cityName}/event/price/{eventId}")
    public String getEventPriceById(@PathVariable int eventId) {
        return eventDao.getEventPriceById(eventId);
    }
}
