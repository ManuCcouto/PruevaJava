package com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceDTO {


        private String brandId;
        private Long productId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String price;
}
