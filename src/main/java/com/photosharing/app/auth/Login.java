package com.photosharing.app.auth;

import javax.validation.constraints.Min;

public class Login {
    @Min(3)
    private String username;
    @Min(8)
    private String password;

    Login(String username, String password) {
        this.password = password;
        this.username = username;
    }
    Login() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
