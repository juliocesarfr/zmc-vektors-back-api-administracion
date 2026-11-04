package com.sysco.api.georeferencia.app.dto.recaudacion;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroCoberturaCarRequest {
    private String codsede;   // null o 'ALL' = todas las sedes
    private String codsuc;    // null o 'ALL' = todas las sucursales
    private String car;       // null o 'ALL' = todos los CAR
    private String fechaini;  // 'yyyy-MM-dd', día de pago
    private String fechafin;  // 'yyyy-MM-dd', día de pago
}
