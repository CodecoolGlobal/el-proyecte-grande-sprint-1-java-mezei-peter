package com.codecool.backendbitter.service.jwt;

import io.jsonwebtoken.Jws;

import javax.naming.AuthenticationException;

public interface JwtService {
    String generateToken(String subject);
    String readToken(String token) throws AuthenticationException;
}
