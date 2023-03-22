package com.codecool.backendbitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

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
    @JsonIgnore
    private Bit bit;

    @JoinColumn
    @ManyToOne
    private User poster;

    @Column
    @CreationTimestamp
    private Timestamp dateOfPosting;

    @Column
    private String bitResponseContent;

    @Column
    @UpdateTimestamp
    private Timestamp timeOfEdit;

    public BitResponse(Bit bit, User poster, String bitResponseContent) {
        this.bit = bit;
        this.poster = poster;
        this.bitResponseContent = bitResponseContent;
    }
}
