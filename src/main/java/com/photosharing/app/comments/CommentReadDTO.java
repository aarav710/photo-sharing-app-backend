package com.photosharing.app.comments;

import com.photosharing.app.users.User;

import java.time.Instant;

public class CommentReadDTO {
    private Integer id;
    private String text;
    private Instant createdAt;
    private User user;
    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}