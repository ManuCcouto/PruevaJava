package com.prueba.java.pruebajava.producto.domain.ports.out;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository {

    Optional<PriceDomain> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(Integer brandId, Long productId, LocalDateTime adjustedDate);

    List<PriceDomain> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderList(Integer brandId, Long productId, LocalDateTime adjustedDate);
}

