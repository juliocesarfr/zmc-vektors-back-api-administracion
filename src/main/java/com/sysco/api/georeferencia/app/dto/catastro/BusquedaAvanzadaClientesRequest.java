package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusquedaAvanzadaClientesRequest {
    private String codsuc;
    private Integer inicio;
    private Integer nropagina;
    private String codcalle;
    private String codcliente;
    private String nromedidor;
    private String codurbaso;
    private String tipodocident;
    private String nrodocumento;
    private String propietario;
    private Integer letrainicio;
    private String nrorecibo;
    private String nropedidoqr;
    private String nropedidovisa;
    private String codsector;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private String codcatastral;
}
