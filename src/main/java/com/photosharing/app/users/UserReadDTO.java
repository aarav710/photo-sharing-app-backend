package com.photosharing.app.users;

import java.time.Instant;

public class UserReadDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String photoUrl;
    private Boolean enabled;
    private Instant createdAt;
}