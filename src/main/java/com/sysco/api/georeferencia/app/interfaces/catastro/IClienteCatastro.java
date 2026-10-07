package com.sysco.api.georeferencia.app.interfaces.catastro;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteCatastroRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteCatastroResponse;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IClienteCatastro {
    List<ClienteCatastroResponse> buscarClienteCatastro(BuscarClienteCatastroRequest request, validar_login userLogin);
}
