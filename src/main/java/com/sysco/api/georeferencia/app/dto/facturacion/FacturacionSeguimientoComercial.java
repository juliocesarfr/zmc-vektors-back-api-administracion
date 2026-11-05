package com.sysco.api.georeferencia.app.dto.facturacion;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturacionSeguimientoComercial implements ConCoordenadas {
    private String codsuc;
    private String sucursal;
    private String codsector;
    private String sector;
    private Integer codcliente;
    private String situacionmed;
    private String propietario;
    private String direccion;
    private String actinac;
    private String estadoservicio;
    private String desestadoservicio;
    private String tiposervicio;
    private String codrutadistribucion;
    private String nroordenrutadist;
    private String codrutalectura;
    private String nroordenrutalect;
    private String catetar;
    private String nomtar;
    private String nummed;
    private String tipopromedio;
    private Double lecturaanterior;
    private Double consumo;
    private Double lecturapromedio;
    private Double consumofac;
    private Double impmesagu;
    private Double impmesalc;
    private String nrodoc;
    private String actividad;
    private Double volumesp;
    private String coddiametro;
    private String desdiametro;
    private String horasabastec;
    private Date fechaaforoini;
    private Date fechainsmed;
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

