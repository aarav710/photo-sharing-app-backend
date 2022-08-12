package com.photosharing.app.posts;

import com.photosharing.app.users.UserReadDTO;

import java.time.Instant;


public class PostReadDTO {
    private Integer id;
    private String caption;
    private String photoUrl;
    private Instant createdAt;
    private UserReadDTO user;
    private Integer likesCount;
    private Integer commentsCount;

    public PostReadDTO(){}

    public PostReadDTO(Integer id, String caption, String photoUrl, Instant createdAt, UserReadDTO user, Integer likesCount, Integer commentsCount) {
        this.id = id;
        this.caption = caption;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
        this.user = user;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public UserReadDTO getUser() {
        return user;
    }

    public void setUser(UserReadDTO user) {
        this.user = user;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }
}