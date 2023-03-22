package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.BitDTO;
import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.service.BitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bit")
@CrossOrigin(origins = "http://localhost:5173")
public class BitController {

    private final BitService bitService;

    public BitController(BitService bitService) {
        this.bitService = bitService;
    }

    @GetMapping("/feed/{userId}")
    public ResponseEntity<List<BitDTO>> getBits(@PathVariable String userId) {
        List<BitDTO> dummyData = List.of(new BitDTO(UUID.randomUUID(),
                new GeneralUserDTO(UUID.randomUUID(), "Dummy User 1", true, false,
                        "Profile Picture"), Timestamp.valueOf(LocalDateTime.now()), "Dummy Content"
        ), new BitDTO(UUID.randomUUID(),
                new GeneralUserDTO(UUID.randomUUID(), "Dummy User 2", true, false,
                        "Profile Picture"), Timestamp.valueOf(LocalDateTime.now()), "Dummy Content"
        ));
        return new ResponseEntity<>(dummyData, HttpStatus.OK);
    }


    @PutMapping("/{bitId}/like/{userId}")
    public ResponseEntity<Void> likeBits(@PathVariable String bitId, @PathVariable String userId) {

        try {
            UUID finalUserId = UUID.fromString(userId);
            UUID finalBitId = UUID.fromString(bitId);

            bitService.likeBit(finalUserId, finalBitId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace(System.err);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
