package com.sysco.api.georeferencia.app.dto.lecturas;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * Lectura y consumo de un cliente en dos periodos (usp_vektors_variacion_consumo).
 * Sin nulos en el JSON: con un ciclo entero son decenas de miles de filas y el peso de la respuesta es lo que más tarda.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariacionConsumoMapa implements ConCoordenadas {
    private String codsuc;
    private Integer codcliente;
    private String codsector;
    private String codmza;
    private String nrolote;
    private String nromed;
    private String propietario;
    private String descripcioncorta;
    private String descripcioncalle;
    private String nrocalle;
    private Long lectura1;
    private Long consumo1;
    private String estadolectura1;
    private Long lectura2;
    private Long consumo2;
    private String estadolectura2;
    private Integer tieneperiodo1;
    private Integer tieneperiodo2;

    private Double lon;
    private Double lat;
}
