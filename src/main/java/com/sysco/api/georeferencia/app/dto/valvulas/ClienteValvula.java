package com.sysco.api.georeferencia.app.dto.valvulas;

import lombok.*;

/** Una fila de usp_sysco_clientes_valvulas: los nombres son las columnas de la función, tal cual. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteValvula {
    private String sucursal;
    private String sector_comercial;
    private Integer codcliente;
    private String cliente;
    private String sector_operacional;
    private String sub_sector_operacional;
    private String sector_abastecimiento;
    private String sub_sector_abastecimiento;
    private String zona;
    private String valvula;
}
