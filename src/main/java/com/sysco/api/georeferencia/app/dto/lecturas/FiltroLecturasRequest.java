package com.sysco.api.georeferencia.app.dto.lecturas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroLecturasRequest {
    private String codsuc;
    private String codsede;
    private String codsector;      // '%' = todos
    private String codciclo;
    private String anio;
    private String mes;
    private String estadolectura;  // '001,002,008' | '' = todos
    private Integer consumoini;    // null = sin límite inferior
    private Integer consumofin;    // null = sin límite superio
    private String tipopromedio;
}
