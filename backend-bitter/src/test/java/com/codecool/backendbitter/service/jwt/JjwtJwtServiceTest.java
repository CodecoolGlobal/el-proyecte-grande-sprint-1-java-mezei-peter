package com.codecool.backendbitter.service.jwt;

import io.jsonwebtoken.io.Decoders;
import org.junit.jupiter.api.Test;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;

class JjwtJwtServiceTest {
    @Test
    void testGenerateToken() {
        JjwtJwtService jjwtJwtService = new JjwtJwtService(Decoders.BASE64.decode(
                "testkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkey"));
        jjwtJwtService.generateToken("test");
    }

    @Test
    void testReadToken_ValidSecretKey() throws AuthenticationException {
        JjwtJwtService jjwtJwtService = new JjwtJwtService(Decoders.BASE64.decode(
                "testkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkey"));

        String token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEifQ.0xX_ccX9BzXIYcBZR71MSGSoqffWheSChwLtt33pudE";

        jjwtJwtService.readToken(token);
    }

    @Test
    void testReadToken_InvalidKeyThrowsError() {
        JjwtJwtService jjwtJwtService = new JjwtJwtService(Decoders.BASE64.decode(
                "testkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkey"));

        String token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEifQ.invalid9BzXIYcBZR71MSGSoqffWheSChwLtinvalid";

        assertThrows(AuthenticationException.class, () -> jjwtJwtService.readToken(token));
    }
}