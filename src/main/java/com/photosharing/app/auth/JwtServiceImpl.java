package com.photosharing.app.auth;

import com.photosharing.app.exceptions.UnauthorizedException;
import com.photosharing.app.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtServiceImpl implements JwtService {

    // token valid for 10 days
    private static final long JWT_VALIDITY = 10 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public boolean validateToken(String token) {
        Claims claims = getClaims(token);
        if (isTokenExpired(getExpirationDate(claims))) {
            return false;
        }

        return true;
    }

    public String createToken(String username)  {
        HashMap<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setExpiration(new Date(JWT_VALIDITY * 1000 + System.currentTimeMillis()))
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Date getExpirationDate(Claims claims) {
        return claims.getExpiration();
    }

    public String getSubject(Claims claims) {
        return claims.getSubject();
    }

    public boolean isTokenExpired(Date date) {
        return date.before(new Date());
    }
}
