package com.sysco.api.georeferencia.app.interfaces.facturacion;

import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionVMARequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionVMA;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionVMARequest;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IFacturacionVMA {
    List<FacturacionVMA> listarFacturacionVMA(FiltroFacturacionVMARequest filtro, validar_login userLogin);
    FacturacionVMA buscarFacturacionVMA(BuscarFacturacionVMARequest filtro, validar_login userLogin);
}
