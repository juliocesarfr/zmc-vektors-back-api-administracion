package com.sysco.api.georeferencia.app.interfaces.catastro;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarModificacionCatastralRequest;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroModificacionesCatastralesRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ModificacionCatastral;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IModificacionesCatastrales {

    List<ModificacionCatastral> listarModificaciones(FiltroModificacionesCatastralesRequest filtro, validar_login userLogin);

    List<ModificacionCatastral> buscarModificaciones(BuscarModificacionCatastralRequest filtro, validar_login userLogin);
}
