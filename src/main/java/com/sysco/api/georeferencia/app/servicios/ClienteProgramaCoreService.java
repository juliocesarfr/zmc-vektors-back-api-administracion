package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.cobranza.ClientesProgramadosPreCorte;
import com.sysco.api.georeferencia.app.dto.cobranza.FiltrarProgramaPrecorte;
import com.sysco.api.georeferencia.app.repositorio.ClienteconProgramaCORERepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ClienteProgramaCoreService {

    @Autowired
    private ClienteconProgramaCORERepositorio repo;

    public Mono<List<ClientesProgramadosPreCorte>> clientesconprogrmadosCore(FiltrarProgramaPrecorte filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.clientesconprogrmadosCore(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
