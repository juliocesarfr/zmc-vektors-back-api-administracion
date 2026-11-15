package com.sysco.api.georeferencia.app.interfaces.lecturas;

import com.sysco.api.georeferencia.app.dto.lecturas.BuscarVariacionPromedioRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.FiltroVariacionPromedioRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.VariacionPromedioMapa;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IVariacionPromedio {

    List<VariacionPromedioMapa> listarVariacionPromedio(FiltroVariacionPromedioRequest filtro, validar_login userLogin);

    VariacionPromedioMapa buscarVariacionPromedio(BuscarVariacionPromedioRequest filtro, validar_login userLogin);
}
