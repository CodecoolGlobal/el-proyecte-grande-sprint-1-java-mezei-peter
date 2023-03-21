package com.codecool.backendbitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
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
        followers.add(follower);
    }

    public void followUser(User user) {
        followedUsers.add(user);
    }
}
