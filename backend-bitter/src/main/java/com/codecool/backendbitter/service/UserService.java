package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.User;

import java.util.UUID;

public interface UserService {
    boolean exists(UserRegistrationDTO usUserRegistrationDTO);
    
    boolean exists(UUID userUUID);

    void saveUser(User user);
    
    void addFollowerToUser(UUID userUUID, UUID followedUserUUID);

    User findById(UUID id);
}
