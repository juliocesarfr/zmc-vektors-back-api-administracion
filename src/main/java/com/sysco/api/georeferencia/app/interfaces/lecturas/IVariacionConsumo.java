package com.sysco.api.georeferencia.app.interfaces.lecturas;

import com.sysco.api.georeferencia.app.dto.lecturas.FiltroVariacionConsumoRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.VariacionConsumoMapa;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IVariacionConsumo {

    List<VariacionConsumoMapa> listarVariacionConsumo(FiltroVariacionConsumoRequest filtro, validar_login userLogin);
}
