package com.tca.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tca.exception.TokenExpiredException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	
    private static final String SECRET_KEY = "Q!XYTNPZ@5NK#THYMELEAF$54MGHPUSH";
    private static final long EXPIRATION_TIME = 120000; // 2 minutes in milliseconds
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new TokenExpiredException("JWT Token expired");
        }
    }

    public String extractUsername(String token) {
    	try {
    		return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    	}
    	catch(Exception ex) {
    		throw new TokenExpiredException("JWT Token expired");
    	}
    }
}
