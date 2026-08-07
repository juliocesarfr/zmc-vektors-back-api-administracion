package com.sysco.api.georeferencia.app.interfaces.vma;

import com.sysco.api.georeferencia.app.dto.vma.BuscarClienteVMARequest;
import com.sysco.api.georeferencia.app.dto.vma.ClientesVMA;
import com.sysco.api.georeferencia.app.dto.vma.FiltroPadronClientesVMARequest;
import com.zmc.sysco.master.clases.dto.validar_login;

import java.util.List;

public interface IClientesVMA {
    List<ClientesVMA> listarPadronClientesNoDomestico(FiltroPadronClientesVMARequest filtro, validar_login userLogin);
    ClientesVMA buscarPadronClientesNoDomestico(BuscarClienteVMARequest filtro, validar_login userLogin);
}
