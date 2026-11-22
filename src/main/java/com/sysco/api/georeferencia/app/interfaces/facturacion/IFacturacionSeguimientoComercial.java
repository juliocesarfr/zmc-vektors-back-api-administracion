package com.sysco.api.georeferencia.app.interfaces.facturacion;

import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionSeguimientoComercialRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionSeguimientoComercial;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionSeguimientoComercialMapa;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionSeguimientoComercialRequest;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IFacturacionSeguimientoComercial {
    List<FacturacionSeguimientoComercialMapa> listarFacturacionSeguimientoComercial(FiltroFacturacionSeguimientoComercialRequest filtro, validar_login userLogin);
    FacturacionSeguimientoComercial buscarFacturacionSeguimientoComercial(BuscarFacturacionSeguimientoComercialRequest filtro, validar_login userLogin);
}

