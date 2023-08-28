package com.guilherme.travelguide.model;

public class Hotel {

    private int hotelId;
    private int hotelCityId;
    private String hotelPicture;
    private String hotelName;
    private String hotelWebsite;
    private String hotelAddress;
    private String hotelPhone;
    private String hotelPrice;

    public Hotel(int hotelId, int hotelCityId, String hotelPicture, String hotelName, String hotelWebsite, String hotelAddress, String hotelPhone, String hotelPrice) {
        this.hotelId = hotelId;
        this.hotelCityId = hotelCityId;
        this.hotelPicture = hotelPicture;
        this.hotelName = hotelName;
        this.hotelWebsite = hotelWebsite;
        this.hotelAddress = hotelAddress;
        this.hotelPhone = hotelPhone;
        this.hotelPrice = hotelPrice;
    }

    public Hotel() {}

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getHotelCityId() {
        return hotelCityId;
    }

    public String getHotelPicture() {
        return hotelPicture;
    }

    public void setHotelPicture(String hotelPicture) {
        this.hotelPicture = hotelPicture;
    }

    public void setHotelCityId(int hotelCityId) {
        this.hotelCityId = hotelCityId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelWebsite() {
        return hotelWebsite;
    }

    public void setHotelWebsite(String hotelWebsite) {
        this.hotelWebsite = hotelWebsite;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

}
