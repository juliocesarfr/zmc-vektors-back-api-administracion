package com.sysco.api.georeferencia.app.dto.valvulas;

import lombok.*;

/** Parámetros de usp_sysco_clientes_valvulas; null en sector, subsector o codcliente = sin filtrar por ese campo. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroClientesValvulasRequest {
    private String sector;
    private String subsector;
    private Integer codcliente;
    private Double distanciametros;
}
