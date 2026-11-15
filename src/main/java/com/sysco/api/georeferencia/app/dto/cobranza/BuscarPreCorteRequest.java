package com.sysco.api.georeferencia.app.dto.cobranza;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPreCorteRequest {
    private String codsuc;
    private Integer codcliente;
    private Integer nroPrecorte;
    // 1 = buscar en la cola de reaperturas del supervisor; no usa nroPrecorte.
    private Integer supervisor;
}