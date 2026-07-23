package com.sysco.api.georeferencia.app.dto.cobranza;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltrarProgramaPrecorte {
    private String codsuc;
    private Integer nroprecorte;
    private String tipooperacion;
}
