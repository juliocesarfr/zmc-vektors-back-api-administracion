package com.sysco.api.georeferencia.app.dto.lecturas;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarVariacionPromedioRequest {
    private String codciclo;
    private String codsuc;
    private String anio;
    private String mes;
    private Integer codcliente;
}
