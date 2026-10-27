package com.sysco.api.georeferencia.app.interfaces.valvulas;

import com.sysco.api.georeferencia.app.dto.valvulas.ClienteValvula;
import com.sysco.api.georeferencia.app.dto.valvulas.FiltroClientesValvulasRequest;

import java.util.List;

public interface IClientesValvulas {

    List<ClienteValvula> listarClientesValvulas(FiltroClientesValvulasRequest filtro);
}
