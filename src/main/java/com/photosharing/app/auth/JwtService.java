package com.photosharing.app.auth;

import com.photosharing.app.users.User;
import io.jsonwebtoken.Claims;

import java.util.Date;

public interface JwtService {
    public boolean validateToken(String token);
    public String createToken(String username);
    public Claims getClaims(String token);

    public Date getExpirationDate(Claims claims);
    public String getSubject(Claims claims);
    public boolean isTokenExpired(Date date);
}
