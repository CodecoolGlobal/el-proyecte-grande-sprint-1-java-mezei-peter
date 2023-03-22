package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.User;

import java.util.Collection;
import java.util.UUID;

public interface UserService {

    User getUserbyId(UUID userId);

    Collection<User> getFollowersForUser(UUID userId);


    Collection<User> getFollowedForUser(UUID userId);


}
