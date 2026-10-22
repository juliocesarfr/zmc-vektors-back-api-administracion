package com.sysco.api.georeferencia.app.dto.cobranza;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

// Solo los campos que usa el seguimiento de programa de cortes y reaperturas (mapa, popup, tabla y Excel).
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientesProgramadosPreCorteMapa implements ConCoordenadas {
    private String codsuc;
    private Integer codcliente;
    private String codsector;
    private String codmza;
    private String nrolote;
    private String propietario;
    private String telefono;
    private String nrocalle;
    private String nromed;
    private Double impmesdeuda;
    private Double impdeuda;
    private Integer nromesesdeuda;
    private String descripcioncorta;
    private String descripcioncalle;
    private String codinspector;
    private String inspector;
    private String descripcionurba;
    private String anio;
    private String mes;
    private String codestado;
    private String estadoservicio2;
    private String diapago;
    private String fcorte;
    private String freapertura;
    private Double impdeudapagada;
    private Double impdeudareclamo;
    private Integer nromesesdeudareclamo;
    private String c_destipocoragu;
    private String c_destipocordes;

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
