package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.BitRepository;
import com.codecool.backendbitter.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class BitServiceImpl implements BitService {

    private final BitRepository bitRepository;

    private final UserRepository userRepository;


    @Autowired
    public BitServiceImpl(BitRepository bitRepository, UserRepository userRepository) {
        this.bitRepository = bitRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<Bit> getBitsForUser(UUID userId) {
        return bitRepository.findBitsByPosterUserId(userId);
    }

    @Override
    public void likeBit(UUID userId, UUID bitId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Bit bit = bitRepository.findById(bitId)
                .orElseThrow(() -> new EntityNotFoundException("Bit not found with ID: " + bitId));

        user.likeBit(bit);
        bit.addUserToLikes(user);

        userRepository.save(user);
        bitRepository.save(bit);
    }

    @Override
    public void removeBitLike(UUID userId, UUID bitId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Bit bit = bitRepository.findById(bitId)
                .orElseThrow(() -> new EntityNotFoundException("Bit not found with ID: " + bitId));

        user.deleteBitFromLikes(bit);
        bit.deleteUserFromLikes(user);

        userRepository.save(user);
        bitRepository.save(bit);

    }


}
