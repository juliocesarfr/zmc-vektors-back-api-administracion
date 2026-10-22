package com.sysco.api.georeferencia.app.dto.vma;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

// Solo los campos que usa el padrón de clientes VMA (mapa, popup, tabla y Excel).
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientesVMAMapa implements ConCoordenadas {
    private Long codcliente;
    private String propietario;
    private String codsector;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private String nromed;
    private String catetar;
    private String actividad;
    private String descripactividad;
    private String estadoservicio;
    private String tiposervicio;
    private String tipopromedio;
    private Double consumo;
    private Double lecturaanterior;
    private Double lecturaultima;
    private Double lecturapromedio;
    private String descripcioncorta;
    private String descripcioncalle;
    private String nrocalle;
    private String flagnuevousu;
    private String feciniactividad;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
