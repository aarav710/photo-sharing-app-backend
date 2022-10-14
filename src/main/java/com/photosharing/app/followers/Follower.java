package com.photosharing.app.followers;

import com.photosharing.app.users.User;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // the user that is being followed
    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private User following;

    // the user that is the follower
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    private Instant createdAt = Instant.now();

    public Follower() {}

    public Follower(User following, User follower) {
        this.following = following;
        this.follower = follower;
    }

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
