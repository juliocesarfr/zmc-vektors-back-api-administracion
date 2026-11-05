package com.sysco.api.georeferencia.app.dto.facturacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscarFacturacionSeguimientoComercialRequest {
    private String codsuc;
    private String codisuc;
    private String anio;
    private String mes;
    private Integer codcliente;

    public String getCodsuc() {
        return codsuc != null ? codsuc : codisuc;
    }
}

