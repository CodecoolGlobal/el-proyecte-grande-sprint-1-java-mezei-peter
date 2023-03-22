package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
