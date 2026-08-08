package com.sysco.api.georeferencia.app.dto.vma;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroPadronClientesVMARequest {
    private String codciclo;
    private String codsuc;
    private String codsector;
    private String estservicio;
    private String tiposervicio;
    private String catetar;
    private String tipousuario;
    private String actividad;
}
