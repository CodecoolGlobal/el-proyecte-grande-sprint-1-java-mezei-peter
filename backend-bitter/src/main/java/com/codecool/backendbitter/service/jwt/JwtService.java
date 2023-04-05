package com.codecool.backendbitter.service.jwt;

import javax.naming.AuthenticationException;

public interface JwtService {
    String generateToken(String subject);
    String readTokenBodySubject(String token) throws AuthenticationException;
}
