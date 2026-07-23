package com.sysco.api.georeferencia.app.excepciones;

import org.springframework.dao.DataAccessException;

@SuppressWarnings("serial")
public class RepositorioExcepcion extends DataAccessException {

    public RepositorioExcepcion(String message) {
        super(message);
    }
    public RepositorioExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

}
