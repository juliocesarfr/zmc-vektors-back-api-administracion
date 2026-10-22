package com.sysco.api.georeferencia.app.dto.lecturas;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarLecturaRequest {
    private Integer op;            // 1: Suministro, 2: DNI/RUC, 3: Nombres
    private String codsuc;
    private String anio;
    private String mes;
    private Object nroSuministro;  // Compatibilidad hacia atrás (acepta numérico o texto)
    private String valor;          // Valor buscado (código, DNI/RUC o nombres)
}
