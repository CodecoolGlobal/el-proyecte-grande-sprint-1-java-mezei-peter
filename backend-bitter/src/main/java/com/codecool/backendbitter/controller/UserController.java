package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        String message;
        HttpStatus status;

        if(userService.exists(userRegistrationDTO)) {
            message = "User already exists!";
            status = HttpStatus.CONFLICT;
        } else {
            try {
                User userToAdd = User
                        .builder()
                        .username(userRegistrationDTO.username())
                        .password(userRegistrationDTO.password())
                        .userEmail(userRegistrationDTO.userEmail())
                        .build();

                message = "User added.";
                status = HttpStatus.OK;
            } catch(Exception e) {
                message = "ERROR.";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(message, status);
    }
}
