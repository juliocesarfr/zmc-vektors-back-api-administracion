package com.sysco.api.georeferencia.app.dto.catastro;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

// Solo los campos que usa el padrón de clientes (mapa, popup, tabla y Excel).
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteTipoActividadMapa implements ConCoordenadas {
    private String codsuc;
    private Integer codcliente;
    private String propietario;
    private String nrocalle;
    private String estadoservicio;
    private String descripcioncalle;
    private String descripcioncorta;
    private String codsector;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private String catetar;
    private String tiposervicio;
    private String nromed;
    private Double lecturaanterior;
    private Double lecturaultima;
    private Double consumo;
    private Double lecturapromedio;
    private String actividad;
    private String feciniactividad;
    private String flagnuevousu;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
