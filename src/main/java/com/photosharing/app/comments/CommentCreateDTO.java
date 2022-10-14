package com.photosharing.app.comments;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentCreateDTO {
    @NotEmpty
    @Size(min = 1, max = 150, message = "Your comment should be at least 1 character long.")
    @Size(max = 150, message = "Your comment should be less than 150 characters long.")
    private String text;

    public CommentCreateDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}