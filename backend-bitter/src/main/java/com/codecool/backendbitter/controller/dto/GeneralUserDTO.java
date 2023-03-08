package com.codecool.backendbitter.controller.dto;

import java.util.UUID;

public record GeneralUserDTO(UUID userId, String userName, boolean isAdmin, boolean isBanned) {
}
