package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.UserRegistrationDTO;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean exists(UserRegistrationDTO userRegistrationDTO) {
        return userRepository.existsUserByUsernameAndPasswordAndUserEmail(
                userRegistrationDTO.username(),
                userRegistrationDTO.password(),
                userRegistrationDTO.userEmail()
        );
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean exists(UUID userId) {
        return userRepository.existsById(userId);
    }

    public User findById(UUID id) {
        return userRepository.findUserByUserId(id);
    }

    public boolean userIsAuthorizedForBitWithId(UUID userId, UUID bitId) {
        User user = userRepository.findUserByUserId(userId);
        if(user == null) return false;
        if(user.isAdmin()) return true;

        boolean userOwnsBit = false;

        if(user.getBits().size() > 0) {
            userOwnsBit = user.getBits().stream().anyMatch(bit -> bit.getBitId().equals(bitId));
        }

        return userOwnsBit;
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

    @Override
    public Collection<User> getFollowersForUser(UUID userId) {
        return userRepository.findFollowersByUserId(userId);
    }

    @Override
    public Collection<User> getFollowedForUser(UUID userId) {
        return userRepository.findFollowedByUserId(userId);
    }

    @Override
    public void addBlockedUserToUser(UUID userId, UUID blockedUserId) {
        User user = userRepository.findById(userId).get();
        User blocked = userRepository.findById(blockedUserId).get();

        user.blockUser(blocked);

        userRepository.save(user);
    }

    @Override
    public String findUserIdByUsername(String username) {
        User user =
                userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return user.getUserId().toString();
    }
}
