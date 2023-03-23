package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.repository.BitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BitServiceImpl implements BitService {
    private final BitRepository bitRepository;

    @Autowired
    public BitServiceImpl(BitRepository bitRepository) {
        this.bitRepository = bitRepository;
    }

    @Override
    public boolean save(Bit bit) {
        try {
            bitRepository.save(bit);
            return true;
        } catch(Throwable e) {
            return false;
        }
    }

    @Override
    public boolean bitExists(UUID id) {
        return bitRepository.existsById(id);
    }

    @Override
    public Bit getById(UUID id) {
        return bitRepository.findById(id).get();
    }
}
