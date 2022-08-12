package com.photosharing.app.auth;

import com.photosharing.app.users.User;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "authorities")
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    private Instant createdAt = Instant.now();

    public Authority(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
