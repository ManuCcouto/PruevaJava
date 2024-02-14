package com.prueba.java.pruebajava.producto.infrastructure.input.controller;

import com.prueba.java.pruebajava.producto.application.services.PriceService;
import com.prueba.java.pruebajava.producto.infrastructure.input.controller.mapper.PriceOutMapper;
import com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prueba")
public class PriceController {

    private PriceService priceService;

    private PriceOutMapper priceMapper;
    @Autowired
    public PriceController(PriceService priceService, PriceOutMapper priceMapper) {
        this.priceService = priceService;
        this.priceMapper = priceMapper;
    }


    @GetMapping("/price")
    public ResponseEntity<PriceDTO> getPricesByBrandIdAndProductIdAndDate(
            @RequestParam Integer brandId,
            @RequestParam Long productId,
            @RequestParam LocalDateTime date) {
        PriceDTO prices = priceMapper.priceToPriceDTO(priceService.getPricesByBrandIdAndProductIdAndDate(brandId, productId, date));
        return ResponseEntity.ok(prices);
    }
}