package com.codecool.backendbitter.controller;

import com.codecool.backendbitter.controller.dto.bit.BitCreationDTO;
import com.codecool.backendbitter.controller.dto.bit.BitDTO;
import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.controller.dto.bit.BitUpdateDTO;
import com.codecool.backendbitter.model.Bit;
import com.codecool.backendbitter.model.User;
import com.codecool.backendbitter.service.BitService;
import com.codecool.backendbitter.service.BitServiceImpl;
import com.codecool.backendbitter.service.UserService;
import com.codecool.backendbitter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BitController {
    private final BitService bitService;
    private final UserService userService;

    @Autowired
    public BitController(BitService bitService, UserService userService) {
        this.bitService = bitService;
        this.userService = userService;
    }

    @GetMapping("/feed/{userId}")
    public ResponseEntity<List<BitDTO>> getBits(@PathVariable String userId) {
        try {
            UUID userUUID = UUID.fromString(userId);
            List<Bit> bitsForUser = userService.arrangeFeed(userUUID);
            List<BitDTO> feedForUser = bitService.convertToBitDTO(bitsForUser);
            return new ResponseEntity<>(feedForUser, HttpStatus.OK);
        } catch(Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBit(@RequestBody BitCreationDTO bitCreationDTO) {
        User posterOfBit = userService.findById(bitCreationDTO.userId());
        if(posterOfBit == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        Bit bitToSave = Bit.builder()
                .poster(posterOfBit)
                .bitContent(bitCreationDTO.bitContent())
                .build();

        if(bitService.save(bitToSave)) {
            return new ResponseEntity<>("Bit saved!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error saving bit!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateBit(@RequestBody BitUpdateDTO bitUpdateDTO) {
        String message = "Bit updated!";
        HttpStatus status = HttpStatus.OK;

        if(!userService.userIsAuthorizedForBitWithId(
                bitUpdateDTO.userId(), bitUpdateDTO.bitId()
        )) {
            message = "User is not authorized to modify this bit.";
            status = HttpStatus.FORBIDDEN;
        } else if (!bitService.bitExists(bitUpdateDTO.bitId())) {
            message = "The requested bit does not exist!";
            status = HttpStatus.NOT_FOUND;
        } else {
            Bit bitToUpdate = bitService.getById(bitUpdateDTO.bitId());
            bitToUpdate.setBitContent(bitUpdateDTO.updatedBitContent());
            bitService.save(bitToUpdate);
        }

        return new ResponseEntity<>(message, status);
    }

    @DeleteMapping("/delete/{userId}/{bitId}")
    public ResponseEntity<String> deleteBit(@PathVariable UUID userId, @PathVariable UUID bitId) {
        String message = "Bit deleted!";
        HttpStatus status = HttpStatus.OK;

        if(!userService.userIsAuthorizedForBitWithId( userId, bitId )) {
            message = "User is not authorized to delete this bit.";
            status = HttpStatus.FORBIDDEN;
        } else if (!bitService.bitExists(bitId)) {
            message = "The requested bit does not exist!";
            status = HttpStatus.NOT_FOUND;
        } else {
            Bit bitToUpdate = bitService.getById(bitId);
            bitService.delete(bitToUpdate);
        }

        return new ResponseEntity<>(message, status);
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

    @DeleteMapping("/{bitId}/like/{userId}")
    public ResponseEntity<Void> removeLikeBits(@PathVariable String bitId, @PathVariable String userId) {

        try {
            UUID finalUserId = UUID.fromString(userId);
            UUID finalBitId = UUID.fromString(bitId);

            bitService.removeBitLike(finalUserId, finalBitId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace(System.err);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
