package com.sysco.api.georeferencia.app.dto.reclamos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarReclamoFundadoInfundadoRequest {
    private String codsede;        // null = todas
    private String fechaini;       // 'yyyy-MM-dd HH:mm:ss'
    private String fechafin;       // 'yyyy-MM-dd HH:mm:ss'
    private String estadoreclamo;  // null = los cuatro
    private Integer codcliente;
}
