package com.guilherme.travelguide.model;

public class Event {

    private int eventId;
    private int eventCityId;
    private String eventPicture;
    private String eventCityName;
    private String eventName;
    private String eventWebsite;
    private String eventAddress;
    private String eventPhone;
    private String eventPrice;

    public Event(int eventId, int eventCityId, String eventPicture, String eventCityName, String eventName, String eventWebsite, String eventAddress, String eventPhone, String eventPrice) {
        this.eventId = eventId;
        this.eventCityId = eventCityId;
        this.eventPicture = eventPicture;
        this.eventCityName = eventCityName;
        this.eventName = eventName;
        this.eventWebsite = eventWebsite;
        this.eventAddress = eventAddress;
        this.eventPhone = eventPhone;
        this.eventPrice = eventPrice;
    }

    public Event(){};

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventCityId() {
        return eventCityId;
    }

    public void setEventCityId(int eventCityId) {
        this.eventCityId = eventCityId;
    }

    public String getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(String eventPicture) {
        this.eventPicture = eventPicture;
    }

    public String getEventCityName() {
        return eventCityName;
    }

    public void setEventCityName(String eventCityName) {
        this.eventCityName = eventCityName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventWebsite() {
        return eventWebsite;
    }

    public void setEventWebsite(String eventWebsite) {
        this.eventWebsite = eventWebsite;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventPhone() {
        return eventPhone;
    }

    public void setEventPhone(String eventPhone) {
        this.eventPhone = eventPhone;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }
}
