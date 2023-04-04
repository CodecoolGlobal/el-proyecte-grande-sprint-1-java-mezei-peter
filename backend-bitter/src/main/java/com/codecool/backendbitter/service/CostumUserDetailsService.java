package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CostumUserDetailsService extends UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CostumUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser =
                userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        String role = applicationUser.isAdmin() ? "ADMIN" : "USER";

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.withUsername(username).password(applicationUser.getUsername()).roles(role).build();

        return userDetails;
    }
}
