package com.prueba.java.pruebajava.producto.infrastructure.output.persistence.repository;


import com.prueba.java.pruebajava.producto.infrastructure.output.persistence.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


import java.util.List;
import java.util.Optional;

public interface SpringDataPriceRepository extends JpaRepository<Price, Long> {



    @Query("SELECT p FROM Price p WHERE p.startDate <= :date AND p.endDate >= :date AND p.productId = :productId AND p.brandId = :brandId ORDER BY p.priority DESC LIMIT 1")
    Optional<Price> findPriceByDateAndProductIdAndBrandId(LocalDateTime date, Long productId, Integer brandId);

    @Query("SELECT p FROM Price p WHERE p.startDate <= :date AND p.endDate >= :date AND p.productId = :productId AND p.brandId = :brandId")
    List<Price> findPriceByDateAndProductIdAndBrandIdList(LocalDateTime date, Long productId, Integer brandId);
}
