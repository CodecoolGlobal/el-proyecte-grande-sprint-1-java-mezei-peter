package com.codecool.backendbitter.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JjwtJwtGenerator implements JwtGenerator {
    @Override
    public String generateToken(String subject) {
        Key key = Keys.hmacShaKeyFor("test_secret_key".getBytes());
        return Jwts.builder()
                .setSubject(subject)
                .signWith(key)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(3L, ChronoUnit.HOURS)))
                .compact();
    }
}
