package com.photosharing.app.posts;

import javax.validation.constraints.Size;

public class PostUpdateDTO {
    @Size(max = 150, message = "Your caption should be less than 150 characters long.")
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
