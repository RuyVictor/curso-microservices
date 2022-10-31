package com.cursomicroservice.msclientes.application;

import com.cursomicroservice.msclientes.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByCpf(String cpf);
}
