package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarModificacionCatastralRequest;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroModificacionesCatastralesRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ModificacionCatastral;
import com.sysco.api.georeferencia.app.repositorio.ModificacionesCatastralesRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ModificacionesCatastralesService {

    @Autowired
    private ModificacionesCatastralesRepositorio repo;

    public Mono<List<ModificacionCatastral>> listarModificaciones(FiltroModificacionesCatastralesRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarModificaciones(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<List<ModificacionCatastral>> buscarModificaciones(BuscarModificacionCatastralRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarModificaciones(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
