package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.repository.BitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class BitServiceImpl implements BitService {

    private BitRepository bitRepository;

    @Autowired
    public BitServiceImpl(BitRepository bitRepository) {
        this.bitRepository = bitRepository;
    }

    @Override
    public Collection<Bit> getBitsForUser(UUID userId) {
        return bitRepository.findBitsByPosterUserId(userId);
    }
}
