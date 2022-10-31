package com.cursomicroservice.msavaliadorcredito.application.exceptions;

public class CardIssuanceException extends RuntimeException {

    public CardIssuanceException(String message) {
        super(message);
    }
}
