package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.repository.BitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitServiceImpl implements BitService {
    private final BitRepository bitRepository;

    @Autowired
    public BitServiceImpl(BitRepository bitRepository) {
        this.bitRepository = bitRepository;
    }

    @Override
    public boolean insertBit(Bit bit) {
        try {
            bitRepository.save(bit);
            return true;
        } catch(Throwable e) {
            return false;
        }
    }
}
