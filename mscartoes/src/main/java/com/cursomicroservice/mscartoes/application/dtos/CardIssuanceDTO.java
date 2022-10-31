package com.cursomicroservice.mscartoes.application.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CardIssuanceDTO {
    private UUID cardId;
    private String cpf;
    private String address;
    private BigDecimal approvedLimit;
}
