package com.sysco.api.georeferencia.app.interfaces.facturacion;

import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionVMARequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionVMA;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionVMARequest;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionVMAMapa;

public interface IFacturacionVMA {
    List<FacturacionVMAMapa> listarFacturacionVMA(FiltroFacturacionVMARequest filtro, validar_login userLogin);
    FacturacionVMA buscarFacturacionVMA(BuscarFacturacionVMARequest filtro, validar_login userLogin);
}
