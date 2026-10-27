package com.sysco.api.georeferencia.app.dto.lecturas;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroVariacionConsumoRequest {
    private String codciclo;
    private String codsuc;
    private String codsede;        // null o 'ALL' = todas
    private String codsector;      // null o 'ALL' = todos
    private String anio;
    private String mes1;           // mes que se toma como base
    private String mes2;           // mes que se compara contra el 1
    private String estadolectura;  // '001,002' | '' o null = todos (del mes 2)
    private Integer consumoini;    // null = sin límite (consumo del mes 2)
    private Integer consumofin;    // null = sin límite (consumo del mes 2)
    private String tipopromedio;   // '' o null = todos (del mes 2)
}
