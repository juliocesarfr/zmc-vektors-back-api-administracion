package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarConexionNuevaRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ConexionNuevaInstalada;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroConexionesNuevasRequest;
import com.sysco.api.georeferencia.app.repositorio.ConexionesNuevasRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ConexionesNuevasService {

    @Autowired
    private ConexionesNuevasRepositorio repo;

    public Mono<List<ConexionNuevaInstalada>> listarConexionesNuevas(FiltroConexionesNuevasRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarConexionesNuevas(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<ConexionNuevaInstalada> buscarConexionNueva(BuscarConexionNuevaRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarConexionNueva(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
