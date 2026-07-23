package com.sysco.api.georeferencia.app.interfaces.cobranza;
import com.sysco.api.georeferencia.app.dto.cobranza.ClientesProgramadosPreCorte;
import com.sysco.api.georeferencia.app.dto.cobranza.FiltrarProgramaPrecorte;
import com.zmc.sysco.master.clases.dto.validar_login;
import java.util.List;
public interface IClientesProgramadosCORE {

    List<ClientesProgramadosPreCorte> clientesconprogrmadosCore(FiltrarProgramaPrecorte filtro,
                                                                validar_login userLogin);
}
