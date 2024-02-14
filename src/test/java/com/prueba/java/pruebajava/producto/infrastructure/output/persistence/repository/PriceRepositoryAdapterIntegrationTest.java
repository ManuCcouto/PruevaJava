package com.prueba.java.pruebajava.producto.infrastructure.output.persistence.repository;


import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PriceRepositoryAdapterIntegrationTest {

    @Autowired
    private PriceRepositoryAdapter priceRepositoryAdapter;


    @Test
    void testFirstPriceOn14thAt10AM_2() {

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 1;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testSecondPriceOn14thAt4PM() {

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 2;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testThirdPriceOn14thAt9PM() {

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 1; // There is only one price at 9 PM on 14th

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testFourthPriceOn15thAt10AM() {

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 3;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testFifthPriceOn16thAt9PM() {

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 4;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }
    @Test
    void testPriceOnDecember31stAt235959() {
        // Define la fecha de solicitud
        LocalDateTime requestTime = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 4;

        // Realiza la consulta al repositorio de precios
        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        // Verifica que se encontró un precio y que tiene el valor esperado para PRICE_LIST
        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testFirstPriceOn14th() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 12, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 1;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testSecondPriceOn14th() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 17, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 2;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testThirdPriceOn14th() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 18, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 2;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testFourthPriceOn14th() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 18, 30, 1);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList =1;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId,productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testFifthPriceOn15th() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 15, 20, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;
        int expectedPriceList = 4;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId,productId, requestTime);

        assertTrue(price.isPresent());
        assertEquals(expectedPriceList, price.get().getPriceList());
    }

    @Test
    void testNoPriceAfterLastDate() {
        LocalDateTime requestTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        Integer brandId = 1;
        Long productId = 35455L;

        Optional<PriceDomain> price = priceRepositoryAdapter.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(brandId, productId, requestTime);

        assertFalse(price.isPresent());
    }
}