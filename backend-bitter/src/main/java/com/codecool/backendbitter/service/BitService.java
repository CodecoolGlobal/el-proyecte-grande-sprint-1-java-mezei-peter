package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.bit.BitDTO;
import com.codecool.backendbitter.model.Bit;

import java.util.List;
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

    List<BitDTO> convertToBitDTO(List<Bit> bitsForUser);
}
