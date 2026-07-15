package com.sysco.api.georeferencia.app.excepciones;

@SuppressWarnings("serial")
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

}
