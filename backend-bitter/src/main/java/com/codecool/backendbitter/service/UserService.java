package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;

public interface UserService {
    boolean exists(UserRegistrationDTO usUserRegistrationDTO);
}
