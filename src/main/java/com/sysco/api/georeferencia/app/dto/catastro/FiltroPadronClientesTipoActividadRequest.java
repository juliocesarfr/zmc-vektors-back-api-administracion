package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroPadronClientesTipoActividadRequest {
    private String codciclo;
    private String codsuc;
    private String codsector;
    private String estservicio;
    private String tiposervicio;
    private String catetar;
    private String urbani;
    private String tipousuario;
    private String actividad;
}