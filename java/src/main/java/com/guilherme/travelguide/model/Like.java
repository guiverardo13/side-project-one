package com.guilherme.travelguide.model;

public class Like {
    private int likeId;
    private int likeCityId;
    private int likeBarId;
    private int likeHotelId;
    private int likeRestaurantId;
    private int likeEventId;
    private int likeTourId;
    private String likePicture;
    private String likeCityName;
    private String likeName;
    private String likeAddress;
    private String likePhone;
    private String likePrice;
    private String likeWebsite;
    private Hotel hotel;
    private Bar bar;
    private Restaurant restaurant;
    private Event event;

    public Like(int likeId, int likeCityId, int likeBarId, int likeHotelId, int likeRestaurantId, int likeEventId, int likeTourId, String likePicture, String likeCityName, String likeName, String likeAddress, String likePhone, String likePrice, String likeWebsite) {
        this.likeId = likeId;
        this.likeCityId = likeCityId;
        this.likeBarId = likeBarId;
        this.likeHotelId = likeHotelId;
        this.likeRestaurantId = likeRestaurantId;
        this.likeEventId = likeEventId;
        this.likeTourId = likeTourId;
        this.likePicture = likePicture;
        this.likeCityName = likeCityName;
        this.likeName = likeName;
        this.likeAddress = likeAddress;
        this.likePhone = likePhone;
        this.likePrice = likePrice;
        this.likeWebsite = likeWebsite;
    }

    public Like(){}

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getLikeCityId() {
        return likeCityId;
    }

    public void setLikeCityId(int likeCityId) {
        this.likeCityId = likeCityId;
    }

    public int getLikeBarId() {
        return likeBarId;
    }

    public void setLikeBarId(int likeBarId) {
        this.likeBarId = likeBarId;
    }

    public int getLikeHotelId() {
        return likeHotelId;
    }

    public void setLikeHotelId(int likeHotelId) {
        this.likeHotelId = likeHotelId;
    }

    public int getLikeRestaurantId() {
        return likeRestaurantId;
    }

    public void setLikeRestaurantId(int likeRestaurantId) {
        this.likeRestaurantId = likeRestaurantId;
    }

    public int getLikeEventId() {
        return likeEventId;
    }

    public void setLikeEventId(int likeEventId) {
        this.likeEventId = likeEventId;
    }

    public int getLikeTourId() {
        return likeTourId;
    }

    public void setLikeTourId(int likeTourId) {
        this.likeTourId = likeTourId;
    }

    public String getLikePicture() {
        return likePicture;
    }

    public void setLikePicture(String likePicture) {
        this.likePicture = likePicture;
    }

    public String getLikeCityName() {
        return likeCityName;
    }

    public void setLikeCityName(String likeCityName) {
        this.likeCityName = likeCityName;
    }

    public String getLikeName() {
        return likeName;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }

    public String getLikeAddress() {
        return likeAddress;
    }

    public void setLikeAddress(String likeAddress) {
        this.likeAddress = likeAddress;
    }

    public String getLikePhone() {
        return likePhone;
    }

    public void setLikePhone(String likePhone) {
        this.likePhone = likePhone;
    }

    public String getLikePrice() {
        return likePrice;
    }

    public void setLikePrice(String likePrice) {
        this.likePrice = likePrice;
    }

    public String getLikeWebsite() {
        return likeWebsite;
    }

    public void setLikeWebsite(String likeWebsite) {
        this.likeWebsite = likeWebsite;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

