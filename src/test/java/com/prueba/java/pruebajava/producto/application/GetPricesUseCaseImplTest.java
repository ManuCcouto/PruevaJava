package com.prueba.java.pruebajava.producto.application;

import com.prueba.java.pruebajava.producto.application.services.PriceService;
import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.domain.ports.out.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetPricesUseCaseImplTest {

    @Autowired
    private PriceService getPricesUseCase;
    @MockBean
    private PriceRepository priceRepository;


    @Test
    void testGetPricesByBrandIdAndProductIdAndDate() {
        //entrada
        Integer brandId = 1;
        Long productId = 35455L;
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        //Objeto del dominio
        PriceDomain expectedPrice = PriceDomain.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1L)
                .productId(35455L)
                .priority(0)
                .price(new BigDecimal("35.55"))
                .currency("EUR")
                .build();
        //mock llamada a base de datos
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(
                brandId, productId, dateTime))
                .thenReturn(Optional.of(expectedPrice));

        PriceDomain actualPriceDomains = getPricesUseCase.getPricesByBrandIdAndProductIdAndDate(brandId, productId, dateTime);

        assertEquals(expectedPrice, actualPriceDomains);
    }

    @Test
    void testGetPricesByBrandIdAndProductIdAndDate_NoPriceFound() {
        //entrada
        Integer brandId = 1;
        Long productId = 35455L;
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(
                brandId, productId, dateTime))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            getPricesUseCase.getPricesByBrandIdAndProductIdAndDate(brandId, productId, dateTime);
        });
    }

    @Test
    void testGetPricesByBrandIdAndProductIdAndDate_NullBrandId() {
        // Entrada con brandId nulo

        Long productId = 35455L;
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 0, 0, 0);

        // Verificación de que se lanza la excepción IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            getPricesUseCase.getPricesByBrandIdAndProductIdAndDate(null, productId, dateTime);
        });
    }


    @Test
    void testGetPricesByBrandIdAndProductIdAndDate_NullProductId() {
        // Entrada con productId nulo
        Integer brandId = 1;
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 0, 0, 0);

        // Verificación de que se lanza la excepción IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            getPricesUseCase.getPricesByBrandIdAndProductIdAndDate(brandId, null, dateTime);
        });
    }

    @Test
    void testGetPricesByBrandIdAndProductIdAndDate_EmptyProductId() {
        // Entrada con productId vacío
        Integer brandId = 1;
        Long productId = 35455L;


        // Verificación de que se lanza la excepción IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            getPricesUseCase.getPricesByBrandIdAndProductIdAndDate(brandId, productId, null);
        });


    }
        ////TEST EN CASO DE QUE LA LOGICA DE BASE DE DATOS FALLE
    @Test
    void testGetPriceListsByBrandIdAndProductIdAndDate_NoPricesFound() {
        Integer brandId = 1;
        Long productId = 35455L;
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        // Mockear repositorio para devolver lista vacía
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderList(1, 35455L, LocalDateTime.of(2022, 1, 1, 0, 0, 0)))
                .thenReturn(List.of());

        // Verificar que se lanza una NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> {
          getPricesUseCase.getPriceListsByBrandIdAndProductIdAndDate(brandId, productId, dateTime);
        });
    }

    @Test
    void testGetPriceListsByBrandIdAndProductIdAndDate_OnePriceWithHighestPriority() {
        // Crear un precio con prioridad alta
        PriceDomain price = PriceDomain.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2022, 12, 31, 23, 59, 59))
                .priceList(1L)
                .productId(35455L)
                .priority(2)
                .price(new BigDecimal("35.55"))
                .currency("EUR")
                .build();

        // Mockear repositorio para devolver un solo precio
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderList(1, 35455L, LocalDateTime.of(2022, 12, 31, 23, 59, 59)))
                .thenReturn(Collections.singletonList(price));

        // Obtener precios y verificar que devuelve el precio con prioridad más alta
        List<PriceDomain> prices = getPricesUseCase.getPriceListsByBrandIdAndProductIdAndDate(1, 35455L, LocalDateTime.of(2022, 12, 31, 23, 59, 59));
        assertEquals(1, prices.size());
        assertEquals(price, prices.get(0));
    }

    @Test
    void testGetPriceListsByBrandIdAndProductIdAndDate_MultiplePricesWithSamePriority() {
        // Crear precios con la misma prioridad pero diferentes precios
        PriceDomain price1 = PriceDomain.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2022, 12, 31, 23, 59, 59))
                .priceList(1L)
                .productId(35455L)
                .priority(2)
                .price(new BigDecimal("35.55"))
                .currency("EUR")
                .build();

        PriceDomain price2 = PriceDomain.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2022, 12, 31, 23, 59, 59))
                .priceList(2L)
                .productId(35455L)
                .priority(2)
                .price(new BigDecimal("45.55"))
                .currency("EUR")
                .build();

        // Mockear repositorio para devolver dos precios con la misma prioridad
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderList(1, 35455L, LocalDateTime.of(2022, 12, 31, 23, 59, 59)))
                .thenReturn(Arrays.asList(price1, price2));

        // Obtener precios y verificar que se devuelve la lista ordenada por precio de mayor a menor
        List<PriceDomain> prices = getPricesUseCase.getPriceListsByBrandIdAndProductIdAndDate(1, 35455L, LocalDateTime.of(2022, 12, 31, 23, 59, 59));
        assertEquals(2, prices.size());
        assertEquals(price2, prices.get(0));
        assertEquals(price1, prices.get(1));
    }

    @Test
    void testGetPriceListsByBrandIdAndProductIdAndDate_MultiplePricesWithDifferentPriorities() {
        // Crear precios con diferentes prioridades y precios
        PriceDomain price1 = PriceDomain.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2022, 12, 31, 23, 59, 59))
                .priceList(1L)
                .productId(35455L)
                .priority(1)
                .price(new BigDecimal("35.55"))
                .currency("EUR")
                .build();

        PriceDomain price2 = PriceDomain.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2022, 12, 31, 23, 59, 59))
                .priceList(2L)
                .productId(35455L)
                .priority(2)
                .price(new BigDecimal("45.55"))
                .currency("EUR")
                .build();

        // Mockear repositorio para devolver dos precios con diferentes prioridades
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderList(1, 35455L, LocalDateTime.now()))
                .thenReturn(Arrays.asList(price1, price2));

        // Obtener precios y verificar que se devuelve solo el precio con la prioridad más alta
        List<PriceDomain> prices = getPricesUseCase.getPriceListsByBrandIdAndProductIdAndDate(1, 35455L, LocalDateTime.now());
        assertEquals(1, prices.size());
        assertEquals(price2, prices.get(0));
    }
}

