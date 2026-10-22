package com.sysco.api.georeferencia.app.interfaces.catastro;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarConexionNuevaRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ConexionNuevaInstalada;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroConexionesNuevasRequest;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IConexionesNuevas {

    List<ConexionNuevaInstalada> listarConexionesNuevas(FiltroConexionesNuevasRequest filtro, validar_login userLogin);

    ConexionNuevaInstalada buscarConexionNueva(BuscarConexionNuevaRequest filtro, validar_login userLogin);
}
