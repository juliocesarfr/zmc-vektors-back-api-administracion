package com.sysco.api.georeferencia.app.dto.reclamos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroReclamosFundadosInfundadosRequest {
    private String codsede;        // null = todas
    private String fechaini;       // 'yyyy-MM-dd HH:mm:ss'
    private String fechafin;       // 'yyyy-MM-dd HH:mm:ss'
    private String estadoreclamo;  // '004', '005', '007', '009' | null = los cuatro
}
