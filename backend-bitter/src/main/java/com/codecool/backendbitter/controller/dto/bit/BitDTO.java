package com.codecool.backendbitter.controller.dto.bit;

import com.codecool.backendbitter.controller.dto.GeneralUserDTO;

import java.sql.Timestamp;
import java.util.UUID;

public record BitDTO(UUID bitId, GeneralUserDTO userDTO, Timestamp dateOfCreation, String content) {
}
