package com.codecool.backendbitter.controller.dto.bit;

import com.codecool.backendbitter.controller.dto.GeneralUserDTO;
import com.codecool.backendbitter.model.Bit;

import java.sql.Timestamp;
import java.util.UUID;

public record BitDTO(UUID bitId, GeneralUserDTO userDTO, Timestamp dateOfCreation, String content) {
    public static BitDTO of (Bit bit) {
        return new BitDTO(
                bit.getBitId(),
                GeneralUserDTO.of(bit.getPoster()),
                bit.getDateOfPosting(),
                bit.getBitContent()
        );
    }
}
