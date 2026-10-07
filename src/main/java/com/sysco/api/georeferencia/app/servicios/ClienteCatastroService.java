package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteCatastroRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteCatastroResponse;
import com.sysco.api.georeferencia.app.repositorio.ClienteCatastroRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ClienteCatastroService {

    @Autowired
    private ClienteCatastroRepositorio repo;

    public Mono<List<ClienteCatastroResponse>> buscarClienteCatastro(BuscarClienteCatastroRequest request, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarClienteCatastro(request, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
