package com.sysco.api.georeferencia.app.dto.lecturas;

// Los setters vacíos dejan que cada DTO del mapa declare solo las coordenadas que su pantalla usa.
public interface ConCoordenadas {
    Number getCodcliente();

    default void setLon(Double lon) {}
    default void setLat(Double lat) {}
    default void setLonpredio(Double lonpredio) {}
    default void setLatpredio(Double latpredio) {}
    default void setLonagua(Double lonagua) {}
    default void setLatagua(Double latagua) {}
    default void setLondesague(Double londesague) {}
    default void setLatdesague(Double latdesague) {}
    default void setLonacometidaagua(Double lonacometidaagua) {}
    default void setLatacometidaagua(Double latacometidaagua) {}
    default void setLonacometidadesague(Double lonacometidadesague) {}
    default void setLatacometidadesague(Double latacometidadesague) {}
}
