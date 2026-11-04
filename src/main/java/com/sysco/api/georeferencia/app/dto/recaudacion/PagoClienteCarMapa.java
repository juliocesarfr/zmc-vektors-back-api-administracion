package com.sysco.api.georeferencia.app.dto.recaudacion;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

/** Pagos válidos de un cliente en un CAR dentro del rango de día de pago. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoClienteCarMapa implements ConCoordenadas {
    private String codsede;
    private String codsuc;
    private String car;
    private String descar;
    private Integer codcliente;
    private String propietario;
    private String direccion;
    private Integer nropagos;
    private Double imptotal;
    private String primerdiapago;
    private String ultimodiapago;

    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
