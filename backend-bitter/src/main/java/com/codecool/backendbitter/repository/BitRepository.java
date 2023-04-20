package com.codecool.backendbitter.repository;

import com.codecool.backendbitter.model.Bit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface BitRepository extends JpaRepository<Bit, UUID> {
    Collection<Bit> findBitsByPosterUserId(@Param("userId") UUID userId);
    Collection<Bit> findBitsByPosterUserId(@Param("userId") UUID userId, Sort sort);

}
