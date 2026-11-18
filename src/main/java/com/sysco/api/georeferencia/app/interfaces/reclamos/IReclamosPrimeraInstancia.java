package com.sysco.api.georeferencia.app.interfaces.reclamos;

import com.sysco.api.georeferencia.app.dto.reclamos.BuscarReclamoPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.FiltroReclamosPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.ReclamoPrimeraInstanciaMapa;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IReclamosPrimeraInstancia {

    List<ReclamoPrimeraInstanciaMapa> listarReclamosPrimeraInstancia(FiltroReclamosPrimeraInstanciaRequest filtro, validar_login userLogin);

    List<ReclamoPrimeraInstanciaMapa> buscarReclamosPrimeraInstancia(BuscarReclamoPrimeraInstanciaRequest filtro, validar_login userLogin);
}
