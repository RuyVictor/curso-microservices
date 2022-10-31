package com.cursomicroservice.mscartoes.application.dtos;

import com.cursomicroservice.mscartoes.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class GetCardsByClientDTO {
    @AllArgsConstructor
    @Data
    public static class Output {
        private UUID id;
        private String name;
        private String brand;
        private BigDecimal cardLimit;

        public static Output fromModel(ClientCard model) {
            return new Output(model.getCard().getId(), model.getCard().getName(), model.getCard().getBrand().toString(), model.getCard().getCardLimit());
        }
    }
}
