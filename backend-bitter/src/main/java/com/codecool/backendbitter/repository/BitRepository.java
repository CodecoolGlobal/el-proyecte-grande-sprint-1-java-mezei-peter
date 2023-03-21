package com.codecool.backendbitter.repository;

import com.codecool.backendbitter.model.Bit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BitRepository extends JpaRepository<Bit, UUID> {
}
