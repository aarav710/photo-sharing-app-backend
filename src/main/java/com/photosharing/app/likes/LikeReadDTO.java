package com.photosharing.app.likes;

import com.photosharing.app.users.User;

import java.time.Instant;

public class LikeReadDTO {
    private Integer id;
    private Instant createdAt;
    private User user;

    public Integer getId() {
        return id;
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

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
