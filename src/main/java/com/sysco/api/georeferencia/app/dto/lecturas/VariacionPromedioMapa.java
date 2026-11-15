package com.sysco.api.georeferencia.app.dto.lecturas;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * Consumo de un cliente frente a su promedio en un periodo (usp_vektors_variacion_promedio).
 * Sin nulos en el JSON: con un ciclo entero son decenas de miles de filas y el peso de la respuesta es lo que más tarda.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariacionPromedioMapa implements ConCoordenadas {
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
    private Long lecturaanterior;
    private Long lectura;
    private Long consumo;
    private Long promedio;
    private String estadolectura;
    private String tipopromedio;
    private Double variacion;

    private Double lon;
    private Double lat;
}
