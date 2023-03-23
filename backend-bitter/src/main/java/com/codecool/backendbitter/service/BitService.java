package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.Bit;

import java.util.UUID;

public interface BitService {
    boolean save(Bit bit);

    boolean bitExists(UUID id);

    Bit getById(UUID id);
}
