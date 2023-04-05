package com.codecool.backendbitter.service.jwt;

public interface JwtService {
    String generateToken(String subject);
    String readToken(String token);
}
