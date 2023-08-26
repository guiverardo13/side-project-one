package com.guilherme.travelguide.model;

public class City {

    private int cityID;
    private String cityName;
    private String stateName;
    private String coverPicture;
    private String cityDescription;
    private String cityVideo;

    public City(int cityID, String cityName, String stateName, String coverPicture, String cityDescription, String cityVideo) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.stateName = stateName;
        this.coverPicture = coverPicture;
        this.cityDescription = cityDescription;
        this.cityVideo = cityVideo;
    }
    public City(){}

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCoverPicture() {
        return coverPicture;
    }
    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public String getCityVideo() {
        return cityVideo;
    }

    public void setCityVideo(String cityVideo) {
        this.cityVideo = cityVideo;
    }
}
