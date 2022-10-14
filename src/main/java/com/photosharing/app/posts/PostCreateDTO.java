package com.photosharing.app.posts;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostCreateDTO {

    @Size(max = 150, message = "Your caption should be less than 150 characters long.")
    private String caption;

    @NotEmpty
    private String photoUrl;

    public PostCreateDTO(){}

    public PostCreateDTO(String caption, String photoUrl) {
        this.caption = caption;
        this.photoUrl = photoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}