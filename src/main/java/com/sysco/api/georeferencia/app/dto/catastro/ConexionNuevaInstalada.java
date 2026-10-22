package com.sysco.api.georeferencia.app.dto.catastro;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConexionNuevaInstalada implements ConCoordenadas {
    private String codsuc;
    private String codsector;
    private String codmza;
    private String nrolote;
    private String nrosublote;
    private String fechaingreso;
    private Integer codcliente;
    private String unidtarifa;
    private String categoria;
    private String tiposervicio;
    private String cliente;
    private String direccion;
    private String nrocalle;
    private String nrodni;
    private String tipoconstruccion;
    private Double nropisos;
    private String tipodeabastecimiento;
    private String actividad;
    private String almacenaje;
    private String estadodeserv;
    private String diametroagua;
    private String tipmateragua;
    private String ubicaccaja;
    private String matcajaagua;
    private String mattapaagua;
    private String estcajaagua;
    private String esttapaagua;
    private String nromedidor;
    private String marcam;
    private String diametrom;
    private String estadomedidor;
    private String situacconexalc;
    private String diametrode;
    private String tipomatde;
    private String loccajade;
    private String materdecaja;
    private String materdetapa;
    private String estadcaja;
    private String estadtapa;
    private String dessucursal;
    private String nomsector;
    private String nommanzana;
    private String anio;
    private String mes;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
