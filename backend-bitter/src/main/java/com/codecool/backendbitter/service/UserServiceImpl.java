package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean exists(UUID userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public void addFollowerToUser(UUID followerId, UUID userId) {
        User user = userRepository.findById(userId).get();
        User follower = userRepository.findById(followerId).get();

        user.addFollower(follower);
        follower.followUser(user);

        userRepository.save(user);
        userRepository.save(follower);
    }
}
