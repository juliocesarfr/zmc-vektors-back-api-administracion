package com.sysco.api.georeferencia.app.interfaces.lecturas;

import com.sysco.api.georeferencia.app.dto.lecturas.FiltroLecturasRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.MeterReadingSector;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;
public interface ILecturas {

    List<MeterReadingSector> listarLecturas(FiltroLecturasRequest filtro,
                                            validar_login userLogin);
}
