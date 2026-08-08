package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarClienteActividadRequest {
    private String codsuc;
    private Integer codcliente;
}
