package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarModificacionCatastralRequest {
    private String codsuc;
    private String codsector;
    private String anio;
    private String mes;
    private String campo;
    private Integer codcliente;
}
