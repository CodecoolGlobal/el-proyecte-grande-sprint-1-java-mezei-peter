package com.codecool.backendbitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(unique = true)
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String userBio;

    @Column
    private String userEmail;

    @Column
    private String profilePicture;

    @Column
    private boolean isAdmin;

    @Column
    private boolean isBanned;

    @Column
    @CreationTimestamp
    private Timestamp dateOfRegistration;

    @Column
    @ManyToMany
    @JsonIgnore
    private Collection<User> followedUsers;

    @Column
    @ManyToMany
    @JsonIgnore
    private Collection<User> followers;

    @JoinColumn
    @ManyToMany
    @JsonIgnore
    private Collection<User> blockedUsers;

    @Column
    @OneToMany
    @JsonIgnore
    private Collection<Bit> bits;

    @JoinColumn
    @ManyToMany
    @JsonIgnore
    private Collection<Bit> likedBits;

    @JoinColumn
    @OneToMany
    @JsonIgnore
    private Collection<BitResponse> bitResponses;

    public void addFollower(User follower) {
        if (followers == null) {
            followers = new HashSet<>();
        }
        followers.add(follower);
    }

    public void followUser(User user) {
        if (followedUsers == null) {
            followedUsers = new HashSet<>();
        }
        followedUsers.add(user);
    }

    public void blockUser(User user) {
        if (blockedUsers == null) {
            blockedUsers = new HashSet<>();
        }
        blockedUsers.add(user);
    }
}
