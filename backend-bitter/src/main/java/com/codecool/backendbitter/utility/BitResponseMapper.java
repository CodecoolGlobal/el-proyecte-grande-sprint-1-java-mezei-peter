package com.codecool.backendbitter.utility;

import com.codecool.backendbitter.controller.dto.BitResponseDTO;
import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.controller.dto.UpdateBitResponseDTO;
import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.repository.BitRepository;
import com.codecool.backendbitter.repository.BitResponseRepository;
import com.codecool.backendbitter.repository.UserRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BitResponseMapper {

    private final UserRepository userRepository;
    private final BitRepository bitRepository;
    private final BitResponseRepository bitResponseRepository;

    @Autowired
    public BitResponseMapper(UserRepository userRepository, BitRepository bitRepository,
                             BitResponseRepository bitResponseRepository) {
        this.userRepository = userRepository;
        this.bitRepository = bitRepository;
        this.bitResponseRepository = bitResponseRepository;
    }

    public BitResponse newBitResponseDTOToBitResponse(NewBitResponseDTO newBitResponseDTO) {
        User bitPoster = userRepository.findById(UUID.fromString(newBitResponseDTO.userId())).orElseThrow();
        Bit bit =
                bitRepository.findById(UUID.fromString(newBitResponseDTO.bitId())).orElseThrow();

        return new BitResponse(bit, bitPoster,
                newBitResponseDTO.bitResponseContent());
    }

    public BitResponse updateBitResponseDTOToBitResponse(UpdateBitResponseDTO updateBitResponseDTO) throws ResourceNotFoundException {
        UUID id = UUID.fromString(updateBitResponseDTO.responseId());
        BitResponse bitResponse =
                bitResponseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bit response with id: " + updateBitResponseDTO.responseId() + " not found"));
        bitResponse.setBitResponseContent(updateBitResponseDTO.newBitContent());

        return bitResponse;
    }

    public BitResponseDTO bitResponseToBitResponseDTO(BitResponse bitResponse) {
        boolean isEdited = bitResponse.getTimeOfEdit().after(bitResponse.getDateOfPosting());

        BitResponseDTO bitResponseDTO = new BitResponseDTO(bitResponse.getBitResponseId(),
                bitResponse.getBitResponseContent(), bitResponse.getDateOfPosting(),
                bitResponse.getPoster().getUserId(), bitResponse.getPoster().getUsername(),
                isEdited);

        return bitResponseDTO;
    }
}
