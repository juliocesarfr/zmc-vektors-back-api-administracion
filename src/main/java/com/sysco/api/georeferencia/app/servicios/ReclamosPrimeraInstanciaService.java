package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.reclamos.BuscarReclamoPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.FiltroReclamosPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.ReclamoPrimeraInstanciaMapa;
import com.sysco.api.georeferencia.app.repositorio.ReclamosPrimeraInstanciaRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ReclamosPrimeraInstanciaService {

    @Autowired
    private ReclamosPrimeraInstanciaRepositorio repo;

    public Mono<List<ReclamoPrimeraInstanciaMapa>> listarReclamosPrimeraInstancia(FiltroReclamosPrimeraInstanciaRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarReclamosPrimeraInstancia(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<List<ReclamoPrimeraInstanciaMapa>> buscarReclamosPrimeraInstancia(BuscarReclamoPrimeraInstanciaRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarReclamosPrimeraInstancia(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
