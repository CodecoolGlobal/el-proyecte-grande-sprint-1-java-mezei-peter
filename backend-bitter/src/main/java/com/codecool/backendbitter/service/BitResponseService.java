package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.BitResponseDTO;
import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.controller.dto.UpdateBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.utility.ResourceNotFoundException;

import java.util.List;

public interface BitResponseService {

    BitResponseDTO addBitResponse(NewBitResponseDTO newBitResponseDTO);
    List<BitResponseDTO> findBitResponsesByBitId(String bitId);
    void deleteBitResponseByBitResponseId(String bitResponseId) throws ResourceNotFoundException;
    void updateBitResponseByBitResponseId(UpdateBitResponseDTO updateBitResponseDTO) throws ResourceNotFoundException;
}
