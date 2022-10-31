package com.cursomicroservice.msclientes.application.dtos;

import com.cursomicroservice.msclientes.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

public abstract class SaveCustomerDTO {
    @Data
    public static class Input {
        private String cpf;
        private String name;
        private Integer age;
    }

    @AllArgsConstructor
    public static class Output extends Customer {
        public Output(Customer customer) {
            super.setId(customer.getId());
            super.setCpf(customer.getCpf());
            super.setAge(customer.getAge());
            super.setName(customer.getName());
        }
    }
}
