package com.sysco.api.georeferencia.app.dto.catastro;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModificacionCatastral implements ConCoordenadas {
    private String codsuc;
    private String codsector;
    private Integer codcliente;
    private String codiusua;
    private String direccion;
    private String propietario;
    private String valorultimo;
    private String valoractual;
    private String observacion;
    private String fechacontrol;
    private String creador;
    private String uso;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
