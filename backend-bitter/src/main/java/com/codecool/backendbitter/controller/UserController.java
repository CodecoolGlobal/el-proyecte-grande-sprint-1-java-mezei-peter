package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.service.UserServiceImpl;
import com.codecool.backendbitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
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

                userService.saveUser(userToAdd);

                message = "User added.";
                status = HttpStatus.OK;
            } catch(Throwable e) {
                message = "ERROR.";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(message, status);
    }

    @PutMapping("/{userId}/follow/{followedUserId}")
    private ResponseEntity<String> followUserByIds(@PathVariable String userId, @PathVariable String followedUserId) {
        try {
            if (userId.equals(followedUserId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            UUID userUUID = UUID.fromString(userId);
            UUID followedUserUUID = UUID.fromString(followedUserId);

            if (!userService.exists(userUUID) || !userService.exists(followedUserUUID)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.addFollowerToUser(userUUID, followedUserUUID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}/block/{blockedUserId}")
    private ResponseEntity<String> blockUserByIds(@PathVariable String userId, @PathVariable String blockedUserId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
