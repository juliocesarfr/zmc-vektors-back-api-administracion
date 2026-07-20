package com.sysco.api.georeferencia.app.dto.lecturas;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filtroresumenxinspector {
    private String codciclo;
    private String codsuc;
    private String codsector;      // '%' = todos
    private String anio;
    private String mes;

}
