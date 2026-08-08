package com.sysco.api.georeferencia.app.dto.facturacion;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturacionVMA {
    private String codemp;
    private String codsuc;
    private String codciclo;
    private Integer codcliente;
    private String codsector;
    private String propietario;
    private String descripcioncorta;
    private String descripcioncalle;
    private String nrocalle;
    private String tiposervicioabrv;
    private String categoria;
    private String tiposervicio;
    private String catetar;
    private Double dbo5_m;
    private Double dqo_m;
    private Double sst_m;
    private Double ayg_m;
    private Double dbo5_v;
    private Double dqo_v;
    private Double sst_v;
    private Double ayg_v;
    private Double dbo5_f;
    private Double dqo_f;
    private Double sst_f;
    private Double ayg_f;
    private Double factortotal;
    private Double nvma;
    private Double impmesagu;
    private Double impmesalc;
    private Double consumofac;
    private String laboratorio;
    private String codciiu;
    private String observacion;
    private Date fechamuestrainopi;
    private Date fecharecepcionresul;
    private Date fechanotificaresultado;
    private Integer muestrainopinada;

    // Campos _m
    private Double al_m;
    private Double as_m;
    private Double b_m;
    private Double cd_m;
    private Double cn_m;
    private Double cu_m;
    private Double cr_m;
    private Double ct_m;
    private Double mn_m;
    private Double hg_m;
    private Double ni_m;
    private Double pb_m;
    private Double sO34_m;
    private Double s4_m;
    private Double zn_m;
    private Double nh4_m;
    private Double ph_m;
    private Double ss_m;
    private Double t_m;

    // Campos _v
    private Double al_v;
    private Double as_v;
    private Double b_v;
    private Double cd_v;
    private Double cn_v;
    private Double cu_v;
    private Double cr_v;
    private Double ct_v;
    private Double mn_v;
    private Double hg_v;
    private Double ni_v;
    private Double pb_v;
    private Double sO34_v;
    private Double s4_v;
    private Double zn_v;
    private Double nh4_v;
    private Double ph_v;
    private Double ss_v;
    private Double t_v;

    // Campos _f
    private Double al_f;
    private Double as_f;
    private Double b_f;
    private Double cd_f;
    private Double cn_f;
    private Double cu_f;
    private Double cr_f;
    private Double ct_f;
    private Double mn_f;
    private Double hg_f;
    private Double ni_f;
    private Double pb_f;
    private Double sO34_f;
    private Double s4_f;
    private Double zn_f;
    private Double nh4_f;
    private Double ph_f;
    private Double ss_f;
    private Double t_f;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
    private Double lonagua;
    private Double latagua;
    private Double londesague;
    private Double latdesague;
    private Double lonacometidaagua;
    private Double latacometidaagua;
    private Double lonacometidadesague;
    private Double latacometidadesague;
}
