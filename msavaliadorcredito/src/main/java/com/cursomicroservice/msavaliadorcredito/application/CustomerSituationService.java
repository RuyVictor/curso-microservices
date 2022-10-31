package com.cursomicroservice.msavaliadorcredito.application;

import com.cursomicroservice.msavaliadorcredito.application.dtos.CardIssuanceDTO;
import com.cursomicroservice.msavaliadorcredito.application.dtos.CustomerValuationDTO;
import com.cursomicroservice.msavaliadorcredito.application.exceptions.CardIssuanceException;
import com.cursomicroservice.msavaliadorcredito.application.exceptions.CustomerDataNotFound;
import com.cursomicroservice.msavaliadorcredito.application.exceptions.MicroserviceComunicationException;
import com.cursomicroservice.msavaliadorcredito.domain.CustomerCard;
import com.cursomicroservice.msavaliadorcredito.domain.CustomerData;
import com.cursomicroservice.msavaliadorcredito.domain.CustomerSituation;
import com.cursomicroservice.msavaliadorcredito.infra.clients.CardResourceClient;
import com.cursomicroservice.msavaliadorcredito.infra.clients.CustomerResourceClient;
import com.cursomicroservice.msavaliadorcredito.infra.mqueue.CardIssuancePublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerSituationService {

    private final CustomerResourceClient customerResourceClient;
    private final CardResourceClient cardsResourceClient;
    private final CardIssuancePublisher cardIssuancePublisher;

    public CustomerSituation getCustomerSituationByCpf(String cpf) throws CustomerDataNotFound, MicroserviceComunicationException {

        try {
            ResponseEntity<CustomerData> customerResponse = customerResourceClient.getOneCustomer(cpf);
            ResponseEntity<List<CustomerCard>> cardsResponse = cardsResourceClient.getCardsByCustomer(cpf);

            return CustomerSituation.builder()
                    .customer(customerResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFound();
            }

            throw new MicroserviceComunicationException(e.getMessage(), status);
        }
    }

    public CustomerValuationDTO.Output createCustomerValuation(CustomerValuationDTO.Input input) throws CustomerDataNotFound, MicroserviceComunicationException {
        try {
            ResponseEntity<CustomerData> customerResponse = customerResourceClient.getOneCustomer(input.getCpf());
            ResponseEntity<List<CustomerCard>> cardsResponse = cardsResourceClient.getCardsWithIncomeLessThanEqual(input.getIncome());

            CustomerData customerData = customerResponse.getBody();
            List<CustomerCard> cards = cardsResponse.getBody();

            var approvedCards = cards.stream().map(card -> {

                BigDecimal basicCardLimit = card.getCardLimit();
                BigDecimal customerAge = BigDecimal.valueOf(customerData.getAge());
                var factor = customerAge.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(basicCardLimit);

                CustomerCard customerCard = new CustomerCard();
                customerCard.setId(card.getId());
                customerCard.setName(card.getName());
                customerCard.setBrand(card.getBrand());
                customerCard.setCardLimit(approvedLimit);

                return customerCard;
            }).collect(Collectors.toList());

            return new CustomerValuationDTO.Output(approvedCards);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFound();
            }

            throw new MicroserviceComunicationException(e.getMessage(), status);
        }
    }

    public CardIssuanceDTO.Output requestCardIssuance(CardIssuanceDTO.Input input) {
        try {
            cardIssuancePublisher.requestCardIssuance(input);
            var protocol = UUID.randomUUID().toString();
            return new CardIssuanceDTO.Output(protocol);
        } catch (Exception e) {
            throw new CardIssuanceException(e.getMessage());
        }
    }
}
