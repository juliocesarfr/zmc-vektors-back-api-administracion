package com.sysco.api.georeferencia.app.interfaces.recaudacion;

import com.sysco.api.georeferencia.app.dto.recaudacion.FiltroCoberturaCarRequest;
import com.sysco.api.georeferencia.app.dto.recaudacion.PagoClienteCarMapa;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface ICoberturaCar {

    List<PagoClienteCarMapa> listarPagosPorCar(FiltroCoberturaCarRequest filtro, validar_login userLogin);
}
