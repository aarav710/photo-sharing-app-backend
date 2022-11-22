package com.photosharing.app.users;

import javax.validation.constraints.Max;

public class UserCreateDTO {
    private String photoUrl;

    @Max(255)
    private String bio;

    public UserCreateDTO(){}

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}