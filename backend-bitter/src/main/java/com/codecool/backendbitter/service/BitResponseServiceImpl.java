package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.repository.BitResponseRepository;
import com.codecool.backendbitter.utility.BitResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
