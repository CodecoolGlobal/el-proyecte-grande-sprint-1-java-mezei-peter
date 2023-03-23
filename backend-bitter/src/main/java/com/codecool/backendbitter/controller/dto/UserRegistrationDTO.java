package com.codecool.backendbitter.controller.dto;

public record UserRegistrationDTO(
        String username,
        String password,
        String userEmail
) {}
