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
                "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjgwNjg0Nzk0LCJleHAiOjE2ODA2OTU1OTR9." +
                "pUh-fNYW463jkoKgJpp2fodFT7bMa024e2Q7r46WP8eRsHAasA_VJ9-7WUDr775c";

        String expectedSubject = "test";
        String resultSubject = jjwtJwtService.readToken(token);
        assertEquals(expectedSubject, resultSubject);
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