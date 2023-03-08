package com.codecool.backendbitter.controller.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record BitDTO(UUID bitId, UserDTO userDTO, Timestamp dateOfCreation, String content) {
}
