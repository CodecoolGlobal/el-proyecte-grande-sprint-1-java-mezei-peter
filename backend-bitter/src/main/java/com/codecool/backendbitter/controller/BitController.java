package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.BitDTO;
import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.model.profilePicture.LowResProfilePicture;
import com.codecool.backendbitter.repository.BitRepository;
import com.codecool.backendbitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bit")
public class BitController {
    private final BitRepository bitRepository;
    private final UserRepository userRepository;

    @Autowired
    public BitController(BitRepository bitRepository, UserRepository userRepository) {
        this.bitRepository = bitRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/feed/{userId}")
    public ResponseEntity<List<BitDTO>> getBits(@PathVariable String userId) {
        List<BitDTO> dummyData = List.of(new BitDTO(UUID.randomUUID(),
                new GeneralUserDTO(UUID.randomUUID(), "Dummy User 1", true, false,
                        "Profile Picture"), Timestamp.valueOf(LocalDateTime.now()) , "Dummy Content"
                ), new BitDTO(UUID.randomUUID(),
                new GeneralUserDTO(UUID.randomUUID(), "Dummy User 2", true, false,
                        "Profile Picture"), Timestamp.valueOf(LocalDateTime.now()) , "Dummy Content"
        ));
        return new ResponseEntity<>(dummyData, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBit() {
        return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);
    }
}
