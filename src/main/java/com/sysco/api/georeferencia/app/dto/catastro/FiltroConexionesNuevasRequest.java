package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroConexionesNuevasRequest {
    private String codciclo;
    private String codsuc;
    private String codsector;
    private String estadoservicio;
    private String tiposervicio;
    private String catetar;
    private String fechaini;          // 'yyyy-MM-dd HH:mm'
    private String fechafin;          // 'yyyy-MM-dd HH:mm'
    private String codtipotramiteing;
    private String actividad;
}
