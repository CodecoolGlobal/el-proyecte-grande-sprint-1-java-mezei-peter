package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.BitResponseDTO;
import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.controller.dto.UpdateBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.service.BitResponseService;
import com.codecool.backendbitter.service.BitResponseServiceImpl;
import com.codecool.backendbitter.utility.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
        BitResponseDTO savedBitResponse = bitResponseService.addBitResponse(newBitResponseDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedBitResponse);
    }

    @GetMapping("/{bitId}")
    public ResponseEntity<?> getBitResponsesByBitId(
            @PathVariable String bitId
    ) {

        List<BitResponseDTO> bitResponseList = bitResponseService.findBitResponsesByBitId(bitId);

        List<BitResponseDTO> dummyResponse = List.of(new BitResponseDTO(UUID.randomUUID(), "Dummy" +
                " Response", Timestamp.valueOf(LocalDateTime.now()), UUID.randomUUID(), "User",
                false));

        return ResponseEntity.status(HttpStatus.OK).body(dummyResponse);
    }

    @DeleteMapping("/{bitId}")
    public ResponseEntity<?> deleteBitResponseByBitId(
            @PathVariable String bitId
    ) {
        try {
            bitResponseService.deleteBitResponseByBitResponseId(bitId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateBitResponseByBitResponseId(
            @RequestBody UpdateBitResponseDTO updateBitResponseDTO
            ) {
        try {
            bitResponseService.updateBitResponseByBitResponseId(updateBitResponseDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Response with id: " + updateBitResponseDTO.responseId() + " updated.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
