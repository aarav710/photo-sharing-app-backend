package com.photosharing.app.followers;

import com.photosharing.app.users.User;

import java.time.Instant;

public class FollowerReadDTO {
    private Integer id;
    private User following;
    private User follower;
    private Instant createdAt = Instant.now();

    public Integer getId() {
        return id;
    }

    public User getFollowing() {
        return following;
    }

    public User getFollower() {
        return follower;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

