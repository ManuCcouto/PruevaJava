package com.prueba.java.pruebajava.producto.infrastructure.input.controller;

import com.prueba.java.pruebajava.producto.application.services.PriceService;
import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;

import com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities.PriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;
    @Mock
    private com.prueba.java.pruebajava.producto.infrastructure.input.controller.mapper.PriceOutMapper priceMapper;
    @InjectMocks
    private com.prueba.java.pruebajava.producto.infrastructure.input.controller.PriceController priceController;

    @Test
    void testGetPricesByBrandIdAndProductIdAndDate_Success() {
        // Datos de prueba
        Integer brandId = 1;
        Long productId = 35455L;
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        PriceDTO expectedPrice = new com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities.PriceDTO();

        // Configurar el comportamiento del servicio
        PriceDomain priceDomain= new PriceDomain();
        when(priceService.getPricesByBrandIdAndProductIdAndDate(brandId, productId, dateTime)).thenReturn(priceDomain);

        // Configurar el comportamiento del mapeador
        when(priceMapper.priceToPriceDTO(priceDomain)).thenReturn(expectedPrice);

        // Ejecutar el método a testear
        ResponseEntity<PriceDTO> response = priceController.getPricesByBrandIdAndProductIdAndDate(brandId, productId, dateTime);

        // Verificar que se devolvió el código de estado esperado
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que el mapeo se realizó correctamente
        assertEquals(expectedPrice, response.getBody());
    }


}

