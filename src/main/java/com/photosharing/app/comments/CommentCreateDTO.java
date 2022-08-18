package com.photosharing.app.comments;

import com.photosharing.app.posts.Post;
import com.photosharing.app.users.User;

public class CommentCreateDTO {
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