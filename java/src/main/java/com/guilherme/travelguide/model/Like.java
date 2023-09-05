package com.guilherme.travelguide.model;

public class Like {

    private int likeId;
    private int likeCityId;
    private int likeBarId;
    private int likeHotelId;
    private int likeRestaurantId;
    private int likeEventId;
    private int likeTourId;

    public Like(int likeId, int likeCityId, int likeBarId, int likeHotelId, int likeRestaurantId, int likeEventId, int likeTourId) {
        this.likeId = likeId;
        this.likeCityId = likeCityId;
        this.likeBarId = likeBarId;
        this.likeHotelId = likeHotelId;
        this.likeRestaurantId = likeRestaurantId;
        this.likeEventId = likeEventId;
        this.likeTourId = likeTourId;
    }

    public Like() {}

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
}
