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
}