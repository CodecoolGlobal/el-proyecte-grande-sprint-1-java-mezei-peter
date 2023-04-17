package com.codecool.backendbitter.controller.dto.user;

import com.codecool.backendbitter.controller.dto.bit.BitDTO;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public record PublicUserProfileDTO(UUID userId, String username, String profilePicture, String userBio,
                                   boolean isAdmin, boolean isBanned, Timestamp dateOfRegistration,
                                   int followerCount, int followedUserCount, Collection<BitDTO> bits) {
}
