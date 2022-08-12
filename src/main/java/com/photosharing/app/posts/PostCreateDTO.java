package com.photosharing.app.posts;



public class PostCreateDTO {
    private String caption;
    private String photoUrl;

    public PostCreateDTO(){}

    public PostCreateDTO(String caption, String photoUrl) {
        this.caption = caption;
        this.photoUrl = photoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}