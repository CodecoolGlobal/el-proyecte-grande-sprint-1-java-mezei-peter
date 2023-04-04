package com.codecool.backendbitter.security;

import com.codecool.backendbitter.service.jwt.JwtGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtGenerator jwtGenerator;

    @Autowired
    public CustomUsernamePasswordAuthenticationFilter(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {

        String jwt = jwtGenerator.generateToken(authResult.getPrincipal().toString());
        System.out.println("username??? " + authResult.getPrincipal().toString());
        System.out.println("token - " + jwt);
        response.setHeader(HttpHeaders.AUTHORIZATION, jwt);
    }
}
