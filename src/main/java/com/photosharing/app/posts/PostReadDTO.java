package com.photosharing.app.posts;


import java.time.Instant;


public class PostReadDTO {
    private Integer id;
    private String photoUrl;
    private Instant createdAt;
    public Integer getId() {
        return id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}