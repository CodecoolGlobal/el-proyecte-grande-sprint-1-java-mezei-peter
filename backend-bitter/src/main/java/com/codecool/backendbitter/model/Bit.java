package com.codecool.backendbitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Bit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bitId;

    @JoinColumn
    @ManyToOne
    private User poster;

    @Column
    @CreationTimestamp
    private Timestamp dateOfPosting;

    @Column
    private String bitContent;

    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Collection<BitResponse> bitResponses;

    @JoinColumn
    @ManyToMany
    private Collection<User> likedBy;


    public void addUserToLikes(User user) {
        if (likedBy == null) {
            likedBy = new HashSet<>();
        }
        likedBy.add(user);
    }

    public void deleteUserFromLikes(User user) {
        likedBy.remove(user);
    }
}
