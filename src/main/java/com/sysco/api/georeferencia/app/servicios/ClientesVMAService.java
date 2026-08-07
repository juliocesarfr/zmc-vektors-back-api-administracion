package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.vma.BuscarClienteVMARequest;
import com.sysco.api.georeferencia.app.dto.vma.ClientesVMA;
import com.sysco.api.georeferencia.app.dto.vma.FiltroPadronClientesVMARequest;
import com.sysco.api.georeferencia.app.repositorio.ClientesVMARepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zmc.sysco.master.clases.dto.validar_login;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ClientesVMAService {

    @Autowired
    private ClientesVMARepositorio repo;

    public Mono<List<ClientesVMA>> listarPadronClientesNoDomestico(FiltroPadronClientesVMARequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarPadronClientesNoDomestico(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<ClientesVMA> buscarPadronClientesNoDomestico(BuscarClienteVMARequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarPadronClientesNoDomestico(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
