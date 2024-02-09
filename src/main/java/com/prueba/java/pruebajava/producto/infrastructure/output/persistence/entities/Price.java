package com.prueba.java.pruebajava.producto.infrastructure.output.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@Table(name = "PRICES")
@AllArgsConstructor
public class Price {


    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "START_DATE")
    private LocalDateTime  startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Id
    @Column(name = "PRICE_LIST")
    private Long priceList;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Double value;

    @Column(name = "CURR")
    private String currency;

    public Price(){
        super();
    }

}