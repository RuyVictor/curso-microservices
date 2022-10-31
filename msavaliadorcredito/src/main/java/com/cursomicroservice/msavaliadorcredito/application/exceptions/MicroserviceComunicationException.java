package com.cursomicroservice.msavaliadorcredito.application.exceptions;

import lombok.Getter;

public class MicroserviceComunicationException extends Exception {
    @Getter
    final private Integer status;

    public MicroserviceComunicationException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
