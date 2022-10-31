package com.cursomicroservice.msavaliadorcredito.infra.clients;

import com.cursomicroservice.msavaliadorcredito.domain.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<CustomerData> getOneCustomer(@RequestParam("cpf") String cpf);
}
