package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.service.BitResponseService;
import com.codecool.backendbitter.service.BitResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{bitId}")
    public ResponseEntity<?> getBitResponsesByBitId(
            @PathVariable String bitId
    ) {
        List<BitResponse> bitResponseList = bitResponseService.findBitResponsesByBitId(bitId);

        return ResponseEntity.status(HttpStatus.OK).body(bitResponseList);
    }
}
