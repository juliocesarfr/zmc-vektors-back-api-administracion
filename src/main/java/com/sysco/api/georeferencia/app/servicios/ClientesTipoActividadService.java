package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteActividadRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteTipoActividad;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroPadronClientesTipoActividadRequest;
import com.sysco.api.georeferencia.app.repositorio.ClientesTipoActividadRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zmc.sysco.master.clases.dto.validar_login;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ClientesTipoActividadService {

    @Autowired
    private ClientesTipoActividadRepositorio repo;

    public Mono<List<ClienteTipoActividad>> listarPadronActividad(FiltroPadronClientesTipoActividadRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarPadronActividad(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<ClienteTipoActividad> buscarClienteActividad(BuscarClienteActividadRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarClienteActividad(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}