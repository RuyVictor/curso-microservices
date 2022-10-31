package com.cursomicroservice.msclientes.application;

import com.cursomicroservice.msclientes.application.dtos.SaveCustomerDTO;
import com.cursomicroservice.msclientes.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public SaveCustomerDTO.Output save(SaveCustomerDTO.Input input) {
        Customer customer = new Customer(null, input.getCpf(), input.getName(), input.getAge());
        Customer savedCustomer = customerRepository.save(customer);
        return new SaveCustomerDTO.Output(savedCustomer);
    }

    public Optional<Customer> getByCPF(String cpf) {
        return customerRepository.findByCpf(cpf);
    }
}
