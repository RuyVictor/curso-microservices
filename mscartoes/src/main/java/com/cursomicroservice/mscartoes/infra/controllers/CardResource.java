package com.cursomicroservice.mscartoes.infra.controllers;

import com.cursomicroservice.mscartoes.application.CardService;
import com.cursomicroservice.mscartoes.application.ClientCardService;
import com.cursomicroservice.mscartoes.application.dtos.GetCardsByClientDTO;
import com.cursomicroservice.mscartoes.application.dtos.SaveCardDTO;
import com.cursomicroservice.mscartoes.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardResource {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @PostMapping
    public ResponseEntity<SaveCardDTO.Output> save(@RequestBody SaveCardDTO.Input card) {
        SaveCardDTO.Output savedCard = cardService.save(card);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("")
                .buildAndExpand(savedCard.getName())
                .toUri();

        return ResponseEntity.created(headerLocation).body(savedCard);
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsWithIncomeLessThanEqual(@RequestParam("income") Long income) {
        List<Card> cards = cardService.getCardsWithIncomeLessThanEqual(income);
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<GetCardsByClientDTO.Output>> getCardsByCustomer(@RequestParam("cpf") String cpf) {
        List<GetCardsByClientDTO.Output> clientCards = clientCardService.getCardsByCustomer(cpf);
        return ResponseEntity.ok().body(clientCards);
    }
}
