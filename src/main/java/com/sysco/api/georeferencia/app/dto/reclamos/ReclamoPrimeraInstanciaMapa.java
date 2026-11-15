package com.sysco.api.georeferencia.app.dto.reclamos;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoPrimeraInstanciaMapa implements ConCoordenadas {
    private Integer nroreclamo;
    private Integer codcliente;
    private String reclamante;
    private String fecharec;
    private String codreclamo;
    private String fechares;
    private Integer resultado;
    private String fechaate;
    private String tiporeclamo;
    private String fecharesolconcil;
    private String creador;
    private String codemp;
    private String codsede;
    private String codsuc;
    private String codoficina;
    private String fechanotificares;
    private String codcatastral;
    private String fechareunion;
    private String estadorec;
    private String codiusua;
    private String fechainspintext;
    private String fechainspinterna;
    private String c_urbaniza;
    private String descripcioncalle;
    private String descripcioncorta;
    private String propietario;
    private String nrocalle;
    private String fechaentrega;
    private String fechamaxresol;
    private Integer procesado;
    private String fechaini;
    private String fechafin;
    private String direccion;
    private String estadoreclamo;
    private String medioreclamo;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
