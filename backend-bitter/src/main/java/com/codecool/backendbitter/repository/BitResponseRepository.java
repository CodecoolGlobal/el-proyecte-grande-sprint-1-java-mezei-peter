package com.codecool.backendbitter.repository;

import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BitResponseRepository extends JpaRepository<BitResponse, UUID> {

    List<BitResponse> findAllByBit_BitId(UUID id);

    @Transactional
    int deleteByBitResponseId(UUID uuid);
}
