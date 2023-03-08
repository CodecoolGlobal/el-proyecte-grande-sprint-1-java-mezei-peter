package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.BitDTO;
import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.model.profilePicture.LowResProfilePicture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bit")
public class BitController {

    @GetMapping("/")
    public ResponseEntity<List<BitDTO>> getBits() {
        List<BitDTO> dummyData = List.of(new BitDTO(UUID.randomUUID(),
                new GeneralUserDTO(UUID.randomUUID(), "Dummy User", true, false,
                        "Profile Picture"), Timestamp.valueOf(LocalDateTime.now()) , "Dummy Content"
                ));
        return new ResponseEntity<>(dummyData, HttpStatus.OK);
    }
}
