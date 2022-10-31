package com.cursomicroservice.msclientes.infra.controllers;

import com.cursomicroservice.msclientes.application.CustomerService;
import com.cursomicroservice.msclientes.application.dtos.SaveCustomerDTO;
import com.cursomicroservice.msclientes.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerResource {

    private final CustomerService customerService;

    @GetMapping
    public String test() {
        log.info("Testando loadBalancer");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<SaveCustomerDTO.Output> save(@RequestBody SaveCustomerDTO.Input client) {
        SaveCustomerDTO.Output savedCustomer = customerService.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("")
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).body(savedCustomer);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Customer> getOneCustomer(@RequestParam("cpf") String cpf) {
        Optional<Customer> customer = customerService.getByCPF(cpf);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(customer.get());
    }
}
