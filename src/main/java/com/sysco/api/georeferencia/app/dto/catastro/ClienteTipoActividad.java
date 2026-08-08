package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteTipoActividad {
    private String codemp;
    private String codsuc;
    private Integer codcliente;
    private String propietario;
    private String codcalle;
    private String nrocalle;
    private String estadoservicio;
    private String descripcioncalle;
    private String descripcioncorta;
    private String codsector;
    private String idestadoservicio;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private String catetar;
    private String tiposervicio;
    private String nromed;
    private Double codrutalectura;
    private Double nroordenrutalect;
    private Double codrutadistribucion;
    private Double nroordenrutadist;
    private String codciclo;
    private String situacionmed;
    private String tipopromedio;
    private Double lecturaanterior;
    private Double lecturaultima;
    private Double consumo;
    private Double lecturapromedio;
    private String actividad;
    private String feciniactividad;
    private Integer unidaddom;
    private Integer unidadcom;
    private Integer unidadind;
    private Integer unidadsoc;
    private Integer unidadest;
    private String flagnuevousu;
    private String fechaanulacion;


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