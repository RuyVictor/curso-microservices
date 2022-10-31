package com.cursomicroservice.mscartoes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand brand;
    @Column
    private BigDecimal income;
    @Column
    private BigDecimal cardLimit;
}
