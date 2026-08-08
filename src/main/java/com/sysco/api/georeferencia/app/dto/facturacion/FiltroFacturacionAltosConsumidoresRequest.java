package com.sysco.api.georeferencia.app.dto.facturacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroFacturacionAltosConsumidoresRequest {
    private String codciclo;
    private String codsuc;
    private String codsector;
    private String codest;
    private String anio;
    private String mes;
    private String tipserv;
    private String catet;
}
