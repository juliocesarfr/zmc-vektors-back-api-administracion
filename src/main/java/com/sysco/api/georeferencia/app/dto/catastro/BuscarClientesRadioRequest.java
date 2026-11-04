package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarClientesRadioRequest {
    private String codsuc;
    private Double lat;
    private Double lon;
    private Double radioMetros;
}
