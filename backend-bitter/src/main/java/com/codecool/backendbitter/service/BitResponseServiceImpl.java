package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.repository.BitResponseRepository;
import com.codecool.backendbitter.utility.BitResponseMapper;
import com.codecool.backendbitter.utility.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BitResponseServiceImpl implements BitResponseService{

    private final BitResponseMapper bitResponseMapper;
    private final BitResponseRepository bitResponseRepository;

    @Autowired
    public BitResponseServiceImpl(BitResponseMapper bitResponseMapper,
                                  BitResponseRepository bitResponseRepository) {
        this.bitResponseMapper = bitResponseMapper;
        this.bitResponseRepository = bitResponseRepository;
    }

    @Override
    public BitResponse addBitResponse(NewBitResponseDTO newBitResponseDTO) {
        BitResponse bitResponse =
                bitResponseMapper.newBitResponseDTOToBitResponse(newBitResponseDTO);
        BitResponse savedBitResponse = bitResponseRepository.save(bitResponse);

        return savedBitResponse;
    }

    @Override
    public List<BitResponse> findBitResponsesByBitId(String bitId) {
        UUID convertedBitId = UUID.fromString(bitId);
        List<BitResponse> bitResponseList =
                bitResponseRepository.findAllByBit_BitId(convertedBitId);
        return bitResponseList;
    }

    public void deleteBitResponseByBitResponseId(String bitResponseId) throws ResourceNotFoundException {
        int affectedRows = bitResponseRepository.deleteByBitResponseId(UUID.fromString(bitResponseId));

        if (affectedRows == 0) throw new ResourceNotFoundException("Bit Response with id: " + bitResponseId + " not found.");
    }
}
