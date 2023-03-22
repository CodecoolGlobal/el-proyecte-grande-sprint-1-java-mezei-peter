package com.codecool.backendbitter.utility;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.BitRepository;
import com.codecool.backendbitter.repository.UserRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BitResponseMapper {

    private final UserRepository userRepository;
    private final BitRepository bitRepository;

    @Autowired
    public BitResponseMapper(UserRepository userRepository, BitRepository bitRepository) {
        this.userRepository = userRepository;
        this.bitRepository = bitRepository;
    }

    public BitResponse newBitResponseDTOToBitResponse(NewBitResponseDTO newBitResponseDTO) {
        User bitPoster = userRepository.findById(UUID.fromString(newBitResponseDTO.userId())).orElseThrow();
        Bit bit =
                bitRepository.findById(UUID.fromString(newBitResponseDTO.bitId())).orElseThrow();

        return new BitResponse(bit, bitPoster,
                newBitResponseDTO.bitResponseContent());
    }
}
