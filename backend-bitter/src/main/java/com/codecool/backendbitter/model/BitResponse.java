package com.codecool.backendbitter.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class BitResponse {
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
    @CreationTimestamp
    private Timestamp dateOfPosting;

    @Column
    private String bitResponseContent;

    public BitResponse(Bit bit, User poster, String bitResponseContent) {
        this.bit = bit;
        this.poster = poster;
        this.bitResponseContent = bitResponseContent;
    }
}
