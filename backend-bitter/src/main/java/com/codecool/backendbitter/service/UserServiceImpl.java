package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean exists(UserRegistrationDTO userRegistrationDTO) {
        return userRepository.existsUserByUsernameAndPasswordAndUserEmail(
                userRegistrationDTO.username(),
                userRegistrationDTO.password(),
                userRegistrationDTO.userEmail()
        );
    }


}
