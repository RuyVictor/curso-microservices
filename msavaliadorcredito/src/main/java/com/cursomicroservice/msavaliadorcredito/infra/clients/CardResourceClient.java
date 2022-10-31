package com.cursomicroservice.msavaliadorcredito.infra.clients;

import com.cursomicroservice.msavaliadorcredito.domain.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<CustomerCard>> getCardsWithIncomeLessThanEqual(@RequestParam("income") Long income);
}
