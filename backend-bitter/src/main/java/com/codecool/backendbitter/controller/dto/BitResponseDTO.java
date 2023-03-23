package com.codecool.backendbitter.controller.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record BitResponseDTO(UUID bitResponseId, String bitResponseContent,
                             Timestamp dateOfPosting, UUID posterUserId, String posterUserName,
                             boolean edited) {
}
