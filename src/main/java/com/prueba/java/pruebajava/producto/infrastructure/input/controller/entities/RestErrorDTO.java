package com.prueba.java.pruebajava.producto.infrastructure.input.controller.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class RestErrorDTO implements Serializable {
        private static final long serialVersionUID = 1L;


        private Integer status;


        private String title;


        private String detail;


        private String timestamp;

    }
