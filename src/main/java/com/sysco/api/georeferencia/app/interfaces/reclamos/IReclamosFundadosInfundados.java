package com.sysco.api.georeferencia.app.interfaces.reclamos;

import com.sysco.api.georeferencia.app.dto.reclamos.BuscarReclamoFundadoInfundadoRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.FiltroReclamosFundadosInfundadosRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.ReclamoFundadoInfundadoMapa;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IReclamosFundadosInfundados {

    List<ReclamoFundadoInfundadoMapa> listarReclamosFundadosInfundados(FiltroReclamosFundadosInfundadosRequest filtro, validar_login userLogin);

    List<ReclamoFundadoInfundadoMapa> buscarReclamosFundadosInfundados(BuscarReclamoFundadoInfundadoRequest filtro, validar_login userLogin);
}
