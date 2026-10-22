package com.sysco.api.georeferencia.app.interfaces.catastro;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteActividadRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteTipoActividad;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroPadronClientesTipoActividadRequest;

import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteTipoActividadMapa;

public interface IClientesTipoActividad {

    List<ClienteTipoActividadMapa> listarPadronActividad(FiltroPadronClientesTipoActividadRequest filtro, validar_login userLogin);

    ClienteTipoActividad buscarClienteActividad(BuscarClienteActividadRequest filtro, validar_login userLogin);

}