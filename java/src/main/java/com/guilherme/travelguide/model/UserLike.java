package com.guilherme.travelguide.model;

public class UserLike {
    private String userId;
    private String likeId;

    public UserLike(String userId, String likeId) {
        this.userId = userId;
        this.likeId = likeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }
}
