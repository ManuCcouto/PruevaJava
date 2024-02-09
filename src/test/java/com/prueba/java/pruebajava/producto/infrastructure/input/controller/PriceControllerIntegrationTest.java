package com.prueba.java.pruebajava.producto.infrastructure.input.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
 class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
     void testGetPricesByBrandIdAndProductIdAndDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prueba/price")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1));
    }
    @Test
     void testGetPricesForNoProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prueba/price")
                        .param("brandId", "1")
                        .param("productId", "9999")
                        .param("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}