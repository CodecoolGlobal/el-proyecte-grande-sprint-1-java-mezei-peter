package com.codecool.backendbitter.repository;

import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByUsernameAndPasswordAndUserEmail(String username, String password, String userEmail);

    User findUserByUserId(UUID userId);
    
    Collection<User> findFollowersByUserId(@Param("userId") UUID userId);

    Collection<User> findFollowedByUserId(@Param("userId") UUID userId);

    Optional<User> findUserByUsername(String username);
}
