package com.photosharing.app.auth;

public class JwtResponse {
    private String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JwtResponse() {}

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        jwtToken = jwtToken;
    }
}
