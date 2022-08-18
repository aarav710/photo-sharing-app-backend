package com.photosharing.app.posts;

public class PostUpdateDTO {
    private String caption;

    public PostUpdateDTO(){}

    public PostUpdateDTO(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
