package com.codecool.backendbitter.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JjwtJwtService implements JwtService {
    private final byte[] secretKey = "test_secret_keytest_secret_keytest_secret_keytest_secret_keytest_secret_key".getBytes();
    @Override
    public String generateToken(String subject) {
        Key key = Keys.hmacShaKeyFor(secretKey);
        return Jwts.builder()
                .setSubject(subject)
                .signWith(key)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(3L, ChronoUnit.HOURS)))
                .compact();
    }

    @Override
    public String readToken(String token) {
        return null;
    }
}
