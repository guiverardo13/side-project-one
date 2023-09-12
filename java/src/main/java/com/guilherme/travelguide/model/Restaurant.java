package com.guilherme.travelguide.model;

public class Restaurant {

    private int restaurantId;
    private int restaurantCityId;
    private String restaurantPicture;
    private String restaurantCityName;
    private String restaurantName;
    private String restaurantWebsite;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantPrice;

    public Restaurant(int restaurantId, int restaurantCityId, String restaurantPicture, String restaurantCityName, String restaurantName, String restaurantWebsite, String restaurantAddress, String restaurantPhone, String restaurantPrice) {
        this.restaurantId = restaurantId;
        this.restaurantCityId = restaurantCityId;
        this.restaurantPicture = restaurantPicture;
        this.restaurantCityName = restaurantCityName;
        this.restaurantName = restaurantName;
        this.restaurantWebsite = restaurantWebsite;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhone = restaurantPhone;
        this.restaurantPrice = restaurantPrice;
    }

    public Restaurant(){}

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getRestaurantCityId() {
        return restaurantCityId;
    }

    public void setRestaurantCityId(int restaurantCityId) {
        this.restaurantCityId = restaurantCityId;
    }

    public String getRestaurantPicture() {
        return restaurantPicture;
    }

    public String getRestaurantCityName() {
        return restaurantCityName;
    }

    public void setRestaurantCityName(String restaurantCityName) {
        this.restaurantCityName = restaurantCityName;
    }

    public void setRestaurantPicture(String restaurantPicture) {
        this.restaurantPicture = restaurantPicture;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantWebsite() {
        return restaurantWebsite;
    }

    public void setRestaurantWebsite(String restaurantWebsite) {
        this.restaurantWebsite = restaurantWebsite;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantPrice() {
        return restaurantPrice;
    }

    public void setRestaurantPrice(String restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }
}
