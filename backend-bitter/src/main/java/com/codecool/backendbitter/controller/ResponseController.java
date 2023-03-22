package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.service.BitResponseService;
import com.codecool.backendbitter.service.BitResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class ResponseController {

    private final BitResponseService bitResponseService;

    @Autowired
    public ResponseController(BitResponseService bitResponseService) {
        this.bitResponseService = bitResponseService;
    }

    @PostMapping("")
    public ResponseEntity<?> addNewBitResponse(
            @RequestBody NewBitResponseDTO newBitResponseDTO
            ) {
        BitResponse savedBitResponse = bitResponseService.addBitResponse(newBitResponseDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedBitResponse);
    }
}
