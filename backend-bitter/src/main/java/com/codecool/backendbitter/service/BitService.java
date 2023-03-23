package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.Bit;

import java.util.UUID;
import java.util.Collection;

public interface BitService {
    boolean save(Bit bit);

    boolean delete(Bit bit);

    boolean bitExists(UUID id);

    Bit getById(UUID id);
    
    Collection<Bit> getBitsForUser(UUID userId);

    void likeBit(UUID userId, UUID bitId);

    void removeBitLike(UUID userId, UUID bitId);
}
