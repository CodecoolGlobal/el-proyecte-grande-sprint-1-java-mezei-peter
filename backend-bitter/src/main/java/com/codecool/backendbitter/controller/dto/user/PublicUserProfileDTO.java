package com.codecool.backendbitter.controller.dto.user;

import com.codecool.backendbitter.controller.dto.bit.BitDTO;
import com.codecool.backendbitter.model.User;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public record PublicUserProfileDTO(UUID userId, String username, String profilePicture, String userBio,
                                   boolean isAdmin, boolean isBanned, Timestamp dateOfRegistration,
                                   int followerCount, int followedUserCount, Collection<BitDTO> bits) {
    public static PublicUserProfileDTO of(User user) {
        return new PublicUserProfileDTO(
                user.getUserId(),
                user.getUsername(),
                user.getProfilePicture(),
                user.getUserBio(),
                user.isAdmin(),
                user.isBanned(),
                user.getDateOfRegistration(),
                user.getFollowers().size(),
                user.getFollowedUsers().size(),
                user.getBits().stream().map(bit -> BitDTO.of(bit)).toList()
        );
    }
}
