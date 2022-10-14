package com.photosharing.app.likes;

import com.photosharing.app.posts.Post;
import com.photosharing.app.users.User;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "likes", indexes = {
        @Index(name = "user_id_index", columnList = "user_id"),
})
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Instant createdAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }
}
