package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.User;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;

public interface UserService {
    boolean exists(UserRegistrationDTO usUserRegistrationDTO);

    boolean exists(UUID userUUID);

    void saveUser(User user);

    void addFollowerToUser(UUID userUUID, UUID followedUserUUID);

    void addBlockedUserToUser(UUID userUUID, UUID blockedUserUUID);

    User findById(UUID id);

    boolean userIsAuthorizedForBitWithId(UUID userId, UUID bitId);

    Collection<User> getFollowersForUser(UUID userId);


    Collection<User> getFollowedForUser(UUID userId);

    String findUserIdByUsername(String username);

    Collection<User> findUsersByUsernameContainingIgnoreCase(String username);
}
