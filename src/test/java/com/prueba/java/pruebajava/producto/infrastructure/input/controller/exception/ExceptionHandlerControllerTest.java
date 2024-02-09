package com.prueba.java.pruebajava.producto.infrastructure.input.controller.exception;


import com.prueba.java.pruebajava.producto.domain.exception.ResourceNotFoundException;
import com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities.RestErrorDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExceptionHandlerControllerTest {

    private final ExceptionHandlerController controller = new ExceptionHandlerController();



    @Test
    void forbiddenExceptionHandler_ReturnsNotFoundStatusAndErrorMessage() {
        NoSuchElementException ex = new NoSuchElementException("Element not found");
        WebRequest request = Mockito.mock(WebRequest.class);

        ResponseEntity<RestErrorDTO> responseEntity = controller.forbiddenException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Element not found", Objects.requireNonNull(responseEntity.getBody()).getTitle());
    }

}
