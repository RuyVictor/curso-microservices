package com.cursomicroservice.mscartoes.infra.mqueue;

import com.cursomicroservice.mscartoes.application.CardRepository;
import com.cursomicroservice.mscartoes.application.ClientCardRepository;
import com.cursomicroservice.mscartoes.application.dtos.CardIssuanceDTO;
import com.cursomicroservice.mscartoes.domain.Card;
import com.cursomicroservice.mscartoes.domain.ClientCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;


    @RabbitListener(queues = "${mq.queue.card-issuance}")
    public void getCardIssuanceRequest(@Payload String payload) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CardIssuanceDTO cardIssuanceFromPayload = mapper.readValue(payload, CardIssuanceDTO.class);
            Card card = cardRepository.findById(cardIssuanceFromPayload.getCardId()).orElseThrow(() -> new Exception("Card not found"));
            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(cardIssuanceFromPayload.getCpf());
            clientCard.setCardLimit(cardIssuanceFromPayload.getApprovedLimit());

            clientCardRepository.save(clientCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
