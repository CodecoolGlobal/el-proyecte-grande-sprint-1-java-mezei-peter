package com.codecool.backendbitter.controller.dto;

import com.codecool.backendbitter.model.profilePicture.ProfilePicture;

import java.util.UUID;

public record GeneralUserDTO(UUID userId, String userName, boolean isAdmin, boolean isBanned,
                             String profilePicture) {
}
