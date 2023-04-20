package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.controller.dto.bit.BitDTO;
import com.codecool.backendbitter.model.Bit;

import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.BitRepository;
import com.codecool.backendbitter.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public boolean save(Bit bit) {
        try {
            bitRepository.save(bit);
            return true;
        } catch(Throwable e) {
            return false;
        }
    }

    @Override
    public boolean delete(Bit bit) {
        try {
            bitRepository.delete(bit);
            return true;
        } catch (EntityNotFoundException e) {
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

    @Override
    public List<BitDTO> convertToBitDTO(List<Bit> bits) {
        return bits.stream()
                .map(bit -> {
                    User user = bit.getPoster();
                    GeneralUserDTO userDTO = new GeneralUserDTO(user.getUserId(), user.getUsername(), user.isAdmin(),
                            user.isBanned(), user.getProfilePicture());
                    return new BitDTO(bit.getBitId(), userDTO, bit.getDateOfPosting(), bit.getBitContent());
                })
                .collect(Collectors.toList());
    }
}
