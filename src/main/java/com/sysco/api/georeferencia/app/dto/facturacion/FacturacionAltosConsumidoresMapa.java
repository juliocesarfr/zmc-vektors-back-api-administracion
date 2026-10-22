package com.sysco.api.georeferencia.app.dto.facturacion;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import java.util.Date;
import lombok.*;

// Solo los campos que usa altos consumidores (mapa, popup, tabla y Excel).
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturacionAltosConsumidoresMapa implements ConCoordenadas {
    private String codsuc;
    private Integer codcliente;
    private String propietario;
    private String direccion;
    private String sector;
    private String nummed;
    private String nomtar;
    private String desestadoservicio;
    private Double lecturaanterior;
    private Double consumo;
    private Double lecturapromedio;
    private String codsector;
    private String catetar;
    private String actividad;
    private Date fechaaforoini;
    private Date fechainsmed;
    private String situacionmed;
    private String tipopromedio;
    private Double volumesp;
    private String estadoservicio;
    private String tiposervicio;
    private Double consumo_1;
    private Double consumo_2;
    private Double consumo_3;
    private Double consumo_4;
    private Double consumo_5;
    private Double consumo_6;
    private String actinac;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
