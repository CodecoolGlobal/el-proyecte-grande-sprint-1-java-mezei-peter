package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.User;

public interface UserService {
    boolean exists(UserRegistrationDTO usUserRegistrationDTO);

    void saveUser(User user);
}
