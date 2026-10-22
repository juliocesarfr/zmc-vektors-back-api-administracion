package com.sysco.api.georeferencia.app.interfaces.facturacion;

import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionAltosConsumidoresRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionAltosConsumidores;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionAltosConsumidoresRequest;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionAltosConsumidoresMapa;

public interface IFacturacionAltosConsumidores {
    List<FacturacionAltosConsumidoresMapa> listarFacturacionAltosConsumidores(FiltroFacturacionAltosConsumidoresRequest filtro, validar_login userLogin);
    FacturacionAltosConsumidores buscarFacturacionAltosConsumidores(BuscarFacturacionAltosConsumidoresRequest filtro, validar_login userLogin);
}
