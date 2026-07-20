package com.sysco.api.georeferencia.app.dto.lecturas;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListadoresumenXinspector {
    private String codinspector, inspector;
    private Integer asignados, enviados,pendientes;
    private Double avance;
}
