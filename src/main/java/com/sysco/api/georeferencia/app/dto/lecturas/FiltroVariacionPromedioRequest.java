package com.sysco.api.georeferencia.app.dto.lecturas;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroVariacionPromedioRequest {
    private String codciclo;
    private String codsuc;
    private String codsede;        // null o 'ALL' = todas
    private String codsector;      // null o 'ALL' = todos
    private String anio;
    private String mes;
    private String estadolectura;  // '001,002' | '' o null = todos
    private Integer consumoini;    // null = sin límite
    private Integer consumofin;    // null = sin límite
    private String tipopromedio;   // '' o null = todos
    private Double variacionini;   // null = sin límite (variación en %)
    private Double variacionfin;   // null = sin límite (variación en %)
}
