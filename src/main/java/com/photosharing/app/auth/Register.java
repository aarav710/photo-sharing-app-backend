package com.photosharing.app.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class Register {
    @Min(3)
    private String username;

    @Min(8)
    private String password;

    @Email
    private String email;

    Register(String username, String password, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }
    Register() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
