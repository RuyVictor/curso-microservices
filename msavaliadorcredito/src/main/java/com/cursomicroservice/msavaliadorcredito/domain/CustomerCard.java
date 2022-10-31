package com.cursomicroservice.msavaliadorcredito.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CustomerCard {
    private UUID id;
    private String name;
    private String brand;
    private BigDecimal cardLimit;
}
