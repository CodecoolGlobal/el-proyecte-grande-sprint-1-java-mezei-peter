package com.codecool.backendbitter.service.jwt;

public interface JwtGenerator {
    String generateToken(String subject);
}
