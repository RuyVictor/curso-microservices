package com.cursomicroservice.msavaliadorcredito.infra.controllers;

import com.cursomicroservice.msavaliadorcredito.application.CustomerSituationService;
import com.cursomicroservice.msavaliadorcredito.application.dtos.CardIssuanceDTO;
import com.cursomicroservice.msavaliadorcredito.application.dtos.CustomerValuationDTO;
import com.cursomicroservice.msavaliadorcredito.application.exceptions.CardIssuanceException;
import com.cursomicroservice.msavaliadorcredito.application.exceptions.CustomerDataNotFound;
import com.cursomicroservice.msavaliadorcredito.application.exceptions.MicroserviceComunicationException;
import com.cursomicroservice.msavaliadorcredito.domain.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-assessor")
@RequiredArgsConstructor
public class CreditAssessorController {

    private final CustomerSituationService customerSituationService;

    @GetMapping(value="customer-situation", params = "cpf")
    public ResponseEntity getCustomerSituation(@RequestParam("cpf") String cpf) {
        try {
            CustomerSituation customerSituation = customerSituationService.getCustomerSituationByCpf(cpf);
            return ResponseEntity.ok().body(customerSituation);
        } catch (CustomerDataNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceComunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createCustomerValuation(@RequestBody CustomerValuationDTO.Input input) {
        try {
            CustomerValuationDTO.Output customerValuation = customerSituationService.createCustomerValuation(input);
            return ResponseEntity.ok().body(customerValuation);
        } catch (CustomerDataNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceComunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("request-card-issuance")
    public ResponseEntity requestCardIssuance(@RequestBody CardIssuanceDTO.Input input) {
        try {
            CardIssuanceDTO.Output cardIssuance = customerSituationService.requestCardIssuance(input);
            return ResponseEntity.ok().body(cardIssuance);
        } catch (CardIssuanceException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
