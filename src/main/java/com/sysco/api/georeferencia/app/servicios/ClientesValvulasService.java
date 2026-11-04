package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.valvulas.ClienteValvula;
import com.sysco.api.georeferencia.app.dto.valvulas.FiltroClientesValvulasRequest;
import com.sysco.api.georeferencia.app.repositorio.ClientesValvulasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ClientesValvulasService {

    @Autowired
    private ClientesValvulasRepositorio repo;

    public Mono<List<ClienteValvula>> listarClientesValvulas(FiltroClientesValvulasRequest filtro) {
        return Mono.fromCallable(() -> this.repo.listarClientesValvulas(filtro))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
