package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;

public interface BitResponseService {

    BitResponse addBitResponse(NewBitResponseDTO newBitResponseDTO);
}
