package com.sysco.api.georeferencia.app.dto.reclamos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroReclamosPrimeraInstanciaRequest {
    private String codsuc;
    private String codsede;
    private String fechaini;     // 'yyyy-MM-dd HH:mm'
    private String fechafin;     // 'yyyy-MM-dd HH:mm'
    private String codreclamo;
    private String tiporeclamo;
    private String codoficina;
}
