package com.codecool.backendbitter.security;

import com.codecool.backendbitter.service.jwt.JwtGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtGenerator jwtGenerator;

    @Autowired
    public CustomUsernamePasswordAuthenticationFilter(JwtGenerator jwtGenerator,
                                                      AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("Attempting authentication.");
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws ServletException, IOException {
        String jwt = jwtGenerator.generateToken(authResult.getPrincipal().toString());
        System.out.println("username??? " + authResult.getPrincipal().toString());
        System.out.println("token - " + jwt);
        response.setHeader(HttpHeaders.AUTHORIZATION, jwt);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        System.out.println("Unsuccessful authentication");
    }
}
