package com.codecool.backendbitter.controller.dto;

import com.codecool.backendbitter.model.profilePicture.ProfilePicture;

import java.sql.Timestamp;
import java.util.UUID;

public record DetailedUserDTO(UUID userId, String userName, boolean isAdmin, boolean isBanned,
                              String userBio, String userEmail, ProfilePicture profilePicture,
                              Timestamp dateOfRegistration) {
}
