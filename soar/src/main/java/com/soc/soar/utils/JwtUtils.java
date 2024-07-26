package com.soc.soar.utils;

import com.soc.soar.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRY_DURATION = 60 * 60; // 1 hour in seconds

    public String generateJwt(User user) {
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + EXPIRY_DURATION * 1000;

        Date issuedAt = new Date(milliTime);
        Date expireAt = new Date(expiryTime);

        Claims claims = Jwts.claims()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expireAt);

        claims.put("email", user.getEmail());
        claims.put("phoneNumber", user.getPhoneNumber());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateJwtToken(String authorization) throws Exception {
        try {
            // Parse and verify the JWT token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(authorization)
                    .getBody();
            return true;  // Token is valid
        } catch (Exception e) {
            return false;  // Token is invalid
        }
    }
}
