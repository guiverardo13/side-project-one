package com.guilherme.travelguide.model;

public class Bar {
    private int barId;
    private int barCityId;
    private String barPicture;
    private String barCityName;
    private String barName;
    private String barWebsite;
    private String barAddress;
    private String barPhone;
    private String barPrice;

    public Bar(int barId, int barCityId, String barPicture, String barCityName, String barName, String barWebsite, String barAddress, String barPhone, String barPrice) {
        this.barId = barId;
        this.barCityId = barCityId;
        this.barPicture = barPicture;
        this.barCityName = barCityName;
        this.barName = barName;
        this.barWebsite = barWebsite;
        this.barAddress = barAddress;
        this.barPhone = barPhone;
        this.barPrice = barPrice;
    }

    public Bar(){}

    public int getBarId() {
        return barId;
    }

    public void setBarId(int barId) {
        this.barId = barId;
    }

    public int getBarCityId() {
        return barCityId;
    }

    public void setBarCityId(int barCityId) {
        this.barCityId = barCityId;
    }

    public String getBarPicture() {
        return barPicture;
    }

    public String getBarCityName() {
        return barCityName;
    }

    public void setBarCityName(String barCityName) {
        this.barCityName = barCityName;
    }

    public void setBarPicture(String barPicture) {
        this.barPicture = barPicture;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getBarWebsite() {
        return barWebsite;
    }

    public void setBarWebsite(String barWebsite) {
        this.barWebsite = barWebsite;
    }

    public String getBarAddress() {
        return barAddress;
    }

    public void setBarAddress(String barAddress) {
        this.barAddress = barAddress;
    }

    public String getBarPhone() {
        return barPhone;
    }

    public void setBarPhone(String barPhone) {
        this.barPhone = barPhone;
    }

    public String getBarPrice() {
        return barPrice;
    }

    public void setBarPrice(String barPrice) {
        this.barPrice = barPrice;
    }
}
