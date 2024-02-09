package com.prueba.java.pruebajava.producto.infrastructure.input.controller.mapper;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities.PriceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T15:14:09+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class PriceOutMapperImpl implements PriceOutMapper {

    @Override
    public PriceDTO priceToPriceDTO(PriceDomain price) {
        if ( price == null ) {
            return null;
        }

        PriceDTO priceDTO = new PriceDTO();

        if ( price.getBrandId() != null ) {
            priceDTO.setBrandId( String.valueOf( price.getBrandId() ) );
        }
        priceDTO.setProductId( price.getProductId() );
        priceDTO.setStartDate( price.getStartDate() );
        priceDTO.setEndDate( price.getEndDate() );

        priceDTO.setPrice( price.getPrice().toString() + " " + price.getCurrency() );

        return priceDTO;
    }

    @Override
    public List<PriceDTO> priceToPriceDTO(List<PriceDomain> price) {
        if ( price == null ) {
            return null;
        }

        List<PriceDTO> list = new ArrayList<PriceDTO>( price.size() );
        for ( PriceDomain priceDomain : price ) {
            list.add( priceToPriceDTO( priceDomain ) );
        }

        return list;
    }
}
