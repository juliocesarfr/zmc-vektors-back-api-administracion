package com.sysco.api.georeferencia.app.dto.lecturas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeterReadingSector {
    private String codemp;
    private String codciclo;
    private String anio;
    private String mes;
    private String codsuc;
    private String codsector;
    private String sector;
    private Integer codcliente;
    private Integer nronotifica;
    private String nromed;
    private Integer lecturaanterior;
    private String fechalecturaant;
    private Integer lecturaultima;
    private String fechalecturault;
    private Integer consumo;
    private String estadomed;
    private String estadomedidor;
    private String estadolectura;
    private String tipoestlectura;
    private Integer lecturapromedio;
    private String obslectura;
    private String marcamed;
    private String tipopromedio;
    private Integer padroncritica;
    private Integer digitacionmovil;
    private Integer ordenenvio;
    private Integer estareg;
    private String codinspector;
    private String inspector;
    private String creador;
    private Integer diaslecturas;
    private String fechareg;
    private String fechaupdate;
    private String userupdate;
    private Integer cdispersos;
    private String situacionmed;
    private Double impmesagu;
    private Double impmesalc;
    private Integer totalreclamos;
    private Integer mesreclamado;

    @JsonProperty("impmes_old")
    private Double impmesOld;
    private String usercritica;
    private String fechacritica;
    private String codsede;
    private String propietario;
    private String codcalle;
    private String nrocalle;
    private String referencia;
    private String catetar;
    private String tarifas;
    private String estadoservicio;
    private String tipoestadoservicio;
    private Integer variasunidadesuso;
    private String codrutalectura;
    private String nroordenrutalect;
    private Integer altocon;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private Integer fuentepropia;
    private Integer porcdescarfuenpropia;
    private String tiposervicio;
    private String codentpub;
    private String actividad;
    private Integer seguimientocomercial;
    private String codiusua;
    private String nomtar;
    private Double volumesp;

    @JsonProperty("c_tipopromedio_med")
    private String cTipopromedioMed;

    private String sucursal;
    private String tipocalle;
    private String tipourba;
    private String urbanizacion;
    private String calle;
    private String descripcioncalle,descripcioncorta;
    private Integer numero;
    private Integer total;
    private String fechamovil,codinspectormovil,codinspectormovil_crit;
    private Integer recibido,web,transferido;
    private String fechamovilcritica;
    private Integer altoconsumidor;
    private Integer lecturacritica;
    private String latitud,longitud;
    private String fasignacionleccampo;
    private Integer lecimpedimento;
   //nuevo
     private String situacion; //ENVIADO/ PENDIENTE /SIN RECIBIR

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
