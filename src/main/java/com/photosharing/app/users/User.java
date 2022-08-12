package com.photosharing.app.users;

import com.photosharing.app.auth.Authority;
import com.photosharing.app.comments.Comment;
import com.photosharing.app.followers.Follower;
import com.photosharing.app.likes.Like;
import com.photosharing.app.posts.Post;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    private String photoUrl;

    private String username;

    private String password;

    private Boolean enabled;

    private Instant createdAt = Instant.now();

    private List<Authority> authorities = new ArrayList<Authority>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<Like>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follower> followers = new ArrayList<Follower>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follower> followings = new ArrayList<Follower>();

    public User(String email, String username, String password, List<Authority> authorities, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.email = email;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public List<Follower> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Follower> followings) {
        this.followings = followings;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}