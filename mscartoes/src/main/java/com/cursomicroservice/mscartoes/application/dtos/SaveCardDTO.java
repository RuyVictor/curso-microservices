package com.cursomicroservice.mscartoes.application.dtos;

import com.cursomicroservice.mscartoes.domain.Card;
import com.cursomicroservice.mscartoes.domain.CardBrand;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

public abstract class SaveCardDTO {
    @Data
    public static class Input {
        private String name;
        private CardBrand brand;
        private BigDecimal income;
        private BigDecimal cardLimit;
    }

    @AllArgsConstructor
    public static class Output extends Card {
        public Output(Card card) {
            super.setId(card.getId());
            super.setName(card.getName());
            super.setBrand(card.getBrand());
            super.setIncome(card.getIncome());
            super.setCardLimit(card.getCardLimit());
        }
    }
}
