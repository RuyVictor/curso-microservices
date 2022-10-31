package com.cursomicroservice.msavaliadorcredito.application.dtos;

import com.cursomicroservice.msavaliadorcredito.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

public abstract class CustomerValuationDTO {

    @Data
    public static class Input {
        private String cpf;
        private Long income;
    }

    @AllArgsConstructor
    @Data
    public static class Output {
        private List<CustomerCard> cards;
    }
}
