package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.controller.dto.UserIdDTO;
import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String userID) {

        try {
            GeneralUserDTO generalUserDTO = userService.findByIdAndConvertTOGeneralUserDTO(UUID.fromString(userID));

            return ResponseEntity.status(HttpStatus.OK).body(generalUserDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        String message;
        HttpStatus status;

        if (userService.exists(userRegistrationDTO)) {
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
            } catch (Throwable e) {
                message = "ERROR.";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(message, status);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) {
        String userId = userService.findUserIdByUsername(username);
        return new ResponseEntity<>(userId, HttpStatus.OK);
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
        try {
            if (userId.equals(blockedUserId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            UUID userUUID = UUID.fromString(userId);
            UUID blockedUserUUID = UUID.fromString(blockedUserId);

            if (!userService.exists(userUUID) || !userService.exists(blockedUserUUID)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            userService.addBlockedUserToUser(userUUID, blockedUserUUID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace(System.err);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    private ResponseEntity<Collection<User>> getUsersByGivenString(@RequestParam String username) {
        try {
            Collection<User> usersByGivenString = userService.findUsersByUsernameContainingIgnoreCase(username);
            return new ResponseEntity<>(usersByGivenString, HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace(System.err);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
