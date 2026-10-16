package com.sysco.api.georeferencia.app.dto.catastro;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusquedaAvanzadaClienteResponse {
    private Integer pseudo_key;
    private String codsuc;
    private Integer codcliente;
    private String propietario;
    private String tipodocident;
    private String nrodocident;
    private String descripcioncorta;
    private String descripcioncalle;
    private String nrocalle;
    private String referencia;
    private String tipourba;
    private String descripcionurba;
    private String nomtar;
    private String idestadoservicio;
    private String estadoservicio;
    private String descripcionestservicio;
    private String desestadoservicio;
    private String nromed;
    private String codsector;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private String codcatastral;
    private String codciclo;
    private Integer codcalle;
    private String telefono;
    private Integer codrutalectura;
    private Integer codrutadistribucion;
    private Integer nroordenrutalect;
    private Integer nroordenrutadist;
    private Integer codclienteund;
    private Boolean und;
    private String tiposervicio;
    private String destiposervicio;
    private Boolean variasunidadesuso;
    private String tipopromedio;
    private String tipousuario;
    private String destipousuario;
    private String estado_agua;
    private String estado_desague;
    private String catetar;
    private String descatetar;
    private String codurbaso;
    private Short unidaddom;
    private Short unidadcom;
    private Short unidadind;
    private Short unidadsoc;
    private Short unidadest;
    private String coddpto;
    private String codprov;
    private String coddist;
    private String departamentos;
    private String provincias;
    private String distritos;
    private String codemp;
    private String fechacorte;
    private String fechareapertura;
    private String descsucursal;
    private String descsector;
    private BigDecimal lecturaanterior;
    private BigDecimal lecturaultima;
    private BigDecimal lecturapromedio;
    private String anio;
    private String mes;
    private String tipocerrado;
    private String fechaprorroga;
    private String fechareinst;
    private String fechainsmed;
    private String fecharetiro;
    private String desentpub;
    private String fechareg;
    private String email;

    // Total records count returned by 'rows' = @rowscount
    private Integer rows;

    // Geographic coordinates from PostGIS
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
