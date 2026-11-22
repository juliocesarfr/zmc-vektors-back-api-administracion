package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRadioResponse {
    private String codemp;
    private String codsuc;
    private Long codcliente;
    private String propietario;
    private String catetar;
    private String nomtar;
    private String actividad;
    private String tipoactividad;
    private String descripcioncalle;
    private String descripcioncorta;
    private String nrocalle;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private String direccion;
    private String estadoservicio;
    private String tipoestadoservicio;
    private Integer unidaddom;
    private Integer unidadcom;
    private Integer unidadind;
    private Integer unidadsoc;
    private Integer unidadest;

    // Coordenadas PostGIS y distancia
    private Double lon;
    private Double lat;
    private Double distanciaMetros;
}

