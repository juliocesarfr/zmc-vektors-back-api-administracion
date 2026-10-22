package com.sysco.api.georeferencia.app.interfaces.cobranza;
import com.sysco.api.georeferencia.app.dto.cobranza.ClientesProgramadosPreCorte;
import com.sysco.api.georeferencia.app.dto.cobranza.FiltrarProgramaPrecorte;
import com.zmc.sysco.master.clases.dto.validar_login;
import java.util.List;
import com.sysco.api.georeferencia.app.dto.cobranza.ClientesProgramadosPreCorteMapa;
public interface IClientesProgramadosCORE {

    List<ClientesProgramadosPreCorteMapa> clientesconprogrmadosCore(FiltrarProgramaPrecorte filtro,
                                                                validar_login userLogin);

    ClientesProgramadosPreCorte buscarPreCortePorCliente(String codsuc, Integer codcliente, Integer nroPrecorte, validar_login userLogin);
}
