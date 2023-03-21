package com.codecool.backendbitter.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class BitResponses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bitResponseId;

    @JoinColumn
    @ManyToOne
    private Bit bit;

    @JoinColumn
    @ManyToOne
    private User poster;

    @Column
    private Timestamp dateOfPosting;

    @Column
    private String bitResponseContent;
}
