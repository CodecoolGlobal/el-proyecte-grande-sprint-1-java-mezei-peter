package com.codecool.backendbitter.service;

import java.util.UUID;

public interface UserService {
    boolean exists(UUID userUUID);

    void addFollowerToUser(UUID userUUID, UUID followedUserUUID);
}
