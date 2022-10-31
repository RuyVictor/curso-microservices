package com.cursomicroservice.msavaliadorcredito.infra.mqueue;

import com.cursomicroservice.msavaliadorcredito.application.dtos.CardIssuanceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuancePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue cardIssuanceQueue;

    public void requestCardIssuance(CardIssuanceDTO.Input input) throws JsonProcessingException {
        var json = convertIntoJson(input);
        rabbitTemplate.convertAndSend(cardIssuanceQueue.getName(), json);
    }

    private String convertIntoJson(CardIssuanceDTO.Input input) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(input);
    }
}
