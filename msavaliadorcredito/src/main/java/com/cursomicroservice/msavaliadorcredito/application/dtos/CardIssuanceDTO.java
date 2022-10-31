package com.cursomicroservice.msavaliadorcredito.application.dtos;

import com.cursomicroservice.msavaliadorcredito.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class CardIssuanceDTO {
    @Data
    public static class Input {
        private UUID cardId;
        private String cpf;
        private String address;
        private BigDecimal approvedLimit;
    }

    @AllArgsConstructor
    @Data
    public static class Output {
        private String protocol;
    }
}
