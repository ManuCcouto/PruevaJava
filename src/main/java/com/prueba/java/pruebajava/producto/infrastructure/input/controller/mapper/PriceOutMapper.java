package com.prueba.java.pruebajava.producto.infrastructure.input.controller.mapper;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PriceOutMapper {

    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "price", expression = "java(price.getPrice().toString() + \" \" + price.getCurrency())")
    PriceDTO priceToPriceDTO(PriceDomain price);
    List<PriceDTO> priceToPriceDTO(List<PriceDomain> price);

}