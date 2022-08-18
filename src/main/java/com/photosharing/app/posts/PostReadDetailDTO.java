package com.photosharing.app.posts;

import com.photosharing.app.users.UserReadDTO;

import java.time.Instant;

public class PostReadDetailDTO {
    private Integer id;
    private String caption;
    private String photoUrl;
    private Instant createdAt;
    private UserReadDTO user;
    private Integer likesCount;
    private Integer commentsCount;

    public Integer getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UserReadDTO getUser() {
        return user;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(UserReadDTO user) {
        this.user = user;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }
}