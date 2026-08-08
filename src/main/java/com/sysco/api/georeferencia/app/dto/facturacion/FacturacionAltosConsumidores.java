package com.sysco.api.georeferencia.app.dto.facturacion;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturacionAltosConsumidores {
    private String codsuc;
    private String codsector;
    private Integer codcliente;
    private String propietario;
    private String direccion;
    private String actinac;
    private String estadoservicio;
    private String tiposervicio;
    private String catetar;
    private String nummed;
    private String tipopromedio;
    private Double consumo;
    private Double lecturapromedio;
    private Double lecturaanterior;
    private Date fechaaforoini;
    private Date fechainsmed;
    private String situacionmed;
    private String actividad;
    private String nomtar;
    private String desestadoservicio;
    private Double volumesp;
    private String sucursal;
    private String sector;
    private Double consumo_1;
    private Double consumo_2;
    private Double consumo_3;
    private Double consumo_4;
    private Double consumo_5;
    private Double consumo_6;

    // Coordenadas PostgreSQL
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
