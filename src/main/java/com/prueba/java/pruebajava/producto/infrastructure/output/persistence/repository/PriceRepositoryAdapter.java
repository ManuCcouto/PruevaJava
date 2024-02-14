package com.prueba.java.pruebajava.producto.infrastructure.output.persistence.repository;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.domain.ports.out.PriceRepository;
import com.prueba.java.pruebajava.producto.infrastructure.output.persistence.entities.Price;
import com.prueba.java.pruebajava.producto.infrastructure.output.persistence.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceRepositoryAdapter implements PriceRepository {
    private final SpringDataPriceRepository springDataPriceRepository;

    private PriceMapper priceMapper;
    @Autowired
    public PriceRepositoryAdapter(SpringDataPriceRepository springDataPriceRepository, PriceMapper priceMapper) {
        this.springDataPriceRepository = springDataPriceRepository;
        this.priceMapper = priceMapper;
    }



    @Override
    public Optional<PriceDomain> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(Integer brandId, Long productId, LocalDateTime adjustedDate) {
        Optional<Price> optionalPrice = springDataPriceRepository.findPriceByDateAndProductIdAndBrandId(adjustedDate, productId, brandId);
        return optionalPrice.map(price -> priceMapper.mapToDomain(price));
    }

    @Override
    public List<PriceDomain> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderList(Integer brandId, Long productId, LocalDateTime adjustedDate) {

       return priceMapper.mapToDomain(springDataPriceRepository.findPriceByDateAndProductIdAndBrandIdList(adjustedDate,productId,brandId));

    }
}
