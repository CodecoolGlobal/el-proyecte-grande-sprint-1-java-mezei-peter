package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.model.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface UserService {
    boolean exists(UserRegistrationDTO usUserRegistrationDTO);

    boolean exists(UUID userUUID);

    void saveUser(User user);

    void addFollowerToUser(UUID userUUID, UUID followedUserUUID) throws Exception;

    void addBlockedUserToUser(UUID userUUID, UUID blockedUserUUID);

    GeneralUserDTO findByIdAndConvertTOGeneralUserDTO(UUID id);

    User findById(UUID id);

    boolean userIsAuthorizedForBitWithId(UUID userId, UUID bitId);

    Collection<User> getFollowersForUser(UUID userId);


    Collection<User> getFollowedForUser(UUID userId);

    String findUserIdByUsername(String username);

    Collection<User> findUsersByUsernameContainingIgnoreCase(String username);

    List<Bit> arrangeFeed(UUID userId);
}
