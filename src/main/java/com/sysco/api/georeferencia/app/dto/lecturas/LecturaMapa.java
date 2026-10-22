package com.sysco.api.georeferencia.app.dto.lecturas;

import lombok.*;

// Solo los campos que pinta el mapa de control de digitación: el listado trae miles de filas
// y con MeterReadingSector completo la respuesta pasaba de 30 MB.
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LecturaMapa implements ConCoordenadas {
    private Integer codcliente;
    private String codsuc;
    private String codsector;
    private String nromed;
    private Integer lecturaanterior;
    private Integer lecturaultima;
    private Integer consumo;
    private String obslectura;
    private String estadolectura;
    private String tipoestlectura;
    private String estadomedidor;
    private String codinspector;
    private String inspector;
    private String propietario;
    private String codmza;
    private String nrolote;
    private String nrocalle;
    private String tarifas;
    private String descripcioncalle;
    private String descripcioncorta;

    //postgresql
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
    private Double lonagua;
    private Double latagua;
    private Double londesague;
    private Double latdesague;
    private Double lonacometidaagua;
    private Double latacometidaagua;
    private Double lonacometidadesague;
    private Double latacometidadesague;
}
