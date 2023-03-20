package com.codecool.backendbitter.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table
public class Bit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bitId;

    @JoinColumn
    @ManyToOne
    private User poster;

    @Column
    private Timestamp dateOfPosting;

    @Column
    private String bitContent;
}
