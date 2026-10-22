package com.sysco.api.georeferencia.app.dto.facturacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroFacturacionVMARequest {
    private String codsuc;
    private String anio;
    private String mes;
}
