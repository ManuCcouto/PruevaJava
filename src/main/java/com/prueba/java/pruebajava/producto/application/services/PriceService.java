package com.prueba.java.pruebajava.producto.application.services;
import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.domain.ports.in.GetPricesUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PriceService implements GetPricesUseCase {

    private final GetPricesUseCase getPricesByBrandIdAndProductIdAndDateUseCase;

    public PriceService(GetPricesUseCase getPricesByBrandIdAndProductIdAndDateUseCase) {
        this.getPricesByBrandIdAndProductIdAndDateUseCase = getPricesByBrandIdAndProductIdAndDateUseCase;
    }

    @Override
    public PriceDomain getPricesByBrandIdAndProductIdAndDate(Integer brandId, Long productId, LocalDateTime date) {
        return getPricesByBrandIdAndProductIdAndDateUseCase.getPricesByBrandIdAndProductIdAndDate(brandId, productId, date);
    }

    @Override
    public List<PriceDomain> getPriceListsByBrandIdAndProductIdAndDate(Integer brandId, Long productId, LocalDateTime date) {
      return  getPricesByBrandIdAndProductIdAndDateUseCase.getPriceListsByBrandIdAndProductIdAndDate(brandId,productId,date);
    }

}

