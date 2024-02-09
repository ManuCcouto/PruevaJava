package com.prueba.java.pruebajava.producto.infrastructure.output.persistence.mapper;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.infrastructure.output.persistence.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceMapper {

    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "priority", target = "priority")
    @Mapping(target = "price", expression = "java(calculatePrice(price))")
    @Mapping(source = "currency", target = "currency")
    PriceDomain mapToDomain(Price price);

    List<PriceDomain> mapToDomain(List<Price> price);
    default BigDecimal calculatePrice(Price price) {
        return BigDecimal.valueOf(price.getValue());
    }

}
