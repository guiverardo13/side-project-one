package com.guilherme.travelguide.model;

public class Tour {

    private int tourId;
    private int tourCityId;
    private String tourPicture;
    private String tourCityName;
    private String tourName;
    private String tourWebsite;
    private String tourAddress;
    private String tourPhone;
    private String tourPrice;

    public Tour(int tourId, int tourCityId, String tourPicture, String tourCityName, String tourName, String tourWebsite, String tourAddress, String tourPhone, String tourPrice) {
        this.tourId = tourId;
        this.tourCityId = tourCityId;
        this.tourPicture = tourPicture;
        this.tourCityName = tourCityName;
        this.tourName = tourName;
        this.tourWebsite = tourWebsite;
        this.tourAddress = tourAddress;
        this.tourPhone = tourPhone;
        this.tourPrice = tourPrice;
    }

    public Tour() {
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getTourCityId() {
        return tourCityId;
    }

    public void setTourCityId(int tourCityId) {
        this.tourCityId = tourCityId;
    }

    public String getTourPicture() {
        return tourPicture;
    }

    public void setTourPicture(String tourPicture) {
        this.tourPicture = tourPicture;
    }

    public String getTourCityName() {
        return tourCityName;
    }

    public void setTourCityName(String tourCityName) {
        this.tourCityName = tourCityName;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourWebsite() {
        return tourWebsite;
    }

    public void setTourWebsite(String tourWebsite) {
        this.tourWebsite = tourWebsite;
    }

    public String getTourAddress() {
        return tourAddress;
    }

    public void setTourAddress(String tourAddress) {
        this.tourAddress = tourAddress;
    }

    public String getTourPhone() {
        return tourPhone;
    }

    public void setTourPhone(String tourPhone) {
        this.tourPhone = tourPhone;
    }

    public String getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(String tourPrice) {
        this.tourPrice = tourPrice;
    }
}
