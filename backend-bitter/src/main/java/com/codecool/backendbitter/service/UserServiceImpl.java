package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserbyId(UUID userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public Collection<User> getFollowersForUser(UUID userId) {
        return userRepository.findFollowersByUserId(userId);
    }

    @Override
    public Collection<User> getFollowedForUser(UUID userId) {
        return userRepository.findFollowedByUserId(userId);
    }
}
