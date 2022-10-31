package com.cursomicroservice.mscartoes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "client_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
    private BigDecimal cardLimit;
}
