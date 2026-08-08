package com.sysco.api.georeferencia.app.dto.cobranza;
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.Builder
public class ClientesProgramadosPreCorte {
    private String codemp,codsuc;
    private Integer codcliente;
    private String codciclo,codsector,codmza,nrolote,nrosublote,propietario,estadoservicio,fechaprorroga,telefono;
    private Double codrutalectura,nroordenrutalect,codrutadistribucion,nroordenrutadist,codcalle;
    private String nrocalle,nromed;
    private Double impmesdeuda,impdeuda;
    private Integer nromesesdeuda;
    private Double impdeudarefin;
    private Integer nromesesrefin;
    private String descripcioncorta,descripcioncalle;
    private Integer nroprecorte;
    private String codinspector,inspector,fechaprogramada,descripcionurba;
    private Integer item;
    private String fechapago,tiposervicio,descripcionservicio,desotros,desotrosmx,corteagua,cortedesague,descripcionestado,anio,mes,codestado,estadoservicio2;
    private String fechavencmto,diapago;
    private String fcorte,freapertura,estadocliente;
    private Integer programacorte,programareapertura;
    private Integer lecturaultima;
    private String tipocorte;
    private Double impdeudapagada;
    private Double impdeudareclamo;
    private Integer nromesesdeudareclamo;
    private String catetar,tarifa;
    private String c_destipocoragu,c_destipocordes;


    //postgresql
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
