package com.sysco.api.georeferencia.app.interfaces.lecturas;

import com.sysco.api.georeferencia.app.dto.lecturas.*;
import com.zmc.sysco.master.clases.dto.validar_login;
import java.util.List;

public interface ILecturas {

    List<MeterReadingSector> listarLecturas(FiltroLecturasRequest filtro,
                                            validar_login userLogin);
    List<ListadoresumenXinspector> resumentomalectura_xinspectore(Filtroresumenxinspector filtro,
                                                                  validar_login userLogin);

    List<MeterReadingSector> detalletomalectura_xinspector(Filtrodetalletomalectura_xinspector filtro,
                                                           validar_login userLogin);
    MeterReadingSector buscarLecturaPorSuministro(String codsuc, String anio, String mes, Integer nroSuministro, validar_login userLogin);
}
