package com.codecool.backendbitter.service;

import com.codecool.backendbitter.model.Bit;

import java.util.Collection;
import java.util.UUID;

public interface BitService {
    Collection<Bit> getBitsForUser(UUID userId);


}
