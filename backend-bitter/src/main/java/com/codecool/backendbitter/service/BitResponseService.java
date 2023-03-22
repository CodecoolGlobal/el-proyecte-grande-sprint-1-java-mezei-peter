package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;

import java.util.List;

public interface BitResponseService {

    BitResponse addBitResponse(NewBitResponseDTO newBitResponseDTO);
    List<BitResponse> findBitResponsesByBitId(String bitId);
}
