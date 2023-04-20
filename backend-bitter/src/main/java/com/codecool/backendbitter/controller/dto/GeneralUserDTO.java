package com.codecool.backendbitter.controller.dto;

import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.model.profilePicture.ProfilePicture;

import java.util.UUID;

public record GeneralUserDTO(UUID userId, String userName, boolean isAdmin, boolean isBanned,
                             String profilePicture) {
    public static GeneralUserDTO of (User user) {
        return new GeneralUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.isAdmin(),
                user.isBanned(),
                user.getProfilePicture()
        );
    }
}
