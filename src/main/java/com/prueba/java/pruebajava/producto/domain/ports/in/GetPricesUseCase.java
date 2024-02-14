package com.prueba.java.pruebajava.producto.domain.ports.in;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;

import java.time.LocalDateTime;
import java.util.List;


public interface GetPricesUseCase {
    PriceDomain getPricesByBrandIdAndProductIdAndDate(Integer brandId, Long productId, LocalDateTime date);
    List<PriceDomain> getPriceListsByBrandIdAndProductIdAndDate(Integer brandId, Long productId, LocalDateTime date);
}
