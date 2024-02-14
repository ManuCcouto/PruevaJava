package com.prueba.java.pruebajava.producto.infrastructure.output.persistence.mapper;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.domain.model.PriceDomain.PriceDomainBuilder;
import com.prueba.java.pruebajava.producto.infrastructure.output.persistence.entities.Price;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-14T17:03:48+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class PriceMapperImpl implements PriceMapper {

    @Override
    public PriceDomain mapToDomain(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceDomainBuilder priceDomain = PriceDomain.builder();

        priceDomain.brandId( price.getBrandId() );
        priceDomain.startDate( price.getStartDate() );
        priceDomain.endDate( price.getEndDate() );
        priceDomain.priceList( price.getPriceList() );
        priceDomain.productId( price.getProductId() );
        priceDomain.priority( price.getPriority() );
        priceDomain.currency( price.getCurrency() );

        priceDomain.price( calculatePrice(price) );

        return priceDomain.build();
    }

    @Override
    public List<PriceDomain> mapToDomain(List<Price> price) {
        if ( price == null ) {
            return null;
        }

        List<PriceDomain> list = new ArrayList<PriceDomain>( price.size() );
        for ( Price price1 : price ) {
            list.add( mapToDomain( price1 ) );
        }

        return list;
    }
}
