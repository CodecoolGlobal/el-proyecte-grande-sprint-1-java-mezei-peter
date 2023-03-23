package com.codecool.backendbitter.controller.dto.bit;

import java.util.UUID;

public record BitUpdateDTO(UUID bitId, UUID userId, String updatedBitContent) {}
