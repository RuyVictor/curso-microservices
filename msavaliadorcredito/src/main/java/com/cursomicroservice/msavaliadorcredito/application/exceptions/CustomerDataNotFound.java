package com.cursomicroservice.msavaliadorcredito.application.exceptions;

public class CustomerDataNotFound extends Exception {
    public CustomerDataNotFound() {
        super("Dados do cliente não consta no CPF informado!");
    }
}
