package com.sysco.api.georeferencia.app.dto.reclamos;

import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoFundadoInfundadoMapa implements ConCoordenadas {
    private String codsuc;
    private String codsede;
    private String codciclo;
    private String codsector;
    private String sucursal;
    private Integer nroreclamo;
    private Integer codcliente;
    private String propietario;
    private String reclamante;
    private String fecharec;
    private String codreclamo;
    private String tiporeclamo;
    private String destiporeclam;
    private String direccion;
    private String desoficina;
    private String estadorec;
    private String estadoreclamo;
    private String medioreclamo;
    private String mesanio;
    private Double montoreclamo;
    private String fechaate;
    private String fecharesolconcil;
    private Integer asistioconciliacion;
    private String fechares;
    private String fechamaxresol;
    private String fechanotificares;
    private String fechalimitenotificacion;
    // Días hábiles desde la fecha del reclamo.
    private Integer diasplazo;
    private Integer diasresolucion;
    private Integer diasnotificacion;
    // SP silencio positivo, FP fuera de plazo, SF notificado sin fecha, AL al límite, PN pendiente de notificar, EP en plazo, NA no aplica.
    private String estadoplazo;
    // 1 = fundado, 0 = infundado.
    private Integer resultado;
    private Integer improcedente;
    private String razonfundado;
    private Integer resultadoprimera;
    private Integer resultadoreconsideracion;
    private String fecharesolrecon;
    private String fechanotificarecon;
    private Integer resultadoapelacion;
    private String fecharesolapela;
    private String fechanotificaapela;
    private Integer revocado;
    // SI, NO, NA (la conciliación no se notifica).
    private String notificacion;
    private Integer procesado;
    private Integer reclamoscliente;
    private Integer cantidadrebajas;
    private Integer nrorebaja;
    private Double totalreba;
    private String fecharebaja;
    // 1 = la rebaja se enlazó por los meses reclamados, no por el número de reclamo.
    private Integer rebajaestimada;
    private String inspector;
    private String notificador;
    private String usuarioresolvio;
    private String fechainspeccion;
    private String resultadoinspeccion;

    // Coordenadas PostgreSQL
    private Double lon;
    private Double lat;
    private Double lonpredio;
    private Double latpredio;
}
