package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.reclamos.BuscarReclamoFundadoInfundadoRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.FiltroReclamosFundadosInfundadosRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.ReclamoFundadoInfundadoMapa;
import com.sysco.api.georeferencia.app.repositorio.ReclamosFundadosInfundadosRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ReclamosFundadosInfundadosService {

    @Autowired
    private ReclamosFundadosInfundadosRepositorio repo;

    public Mono<List<ReclamoFundadoInfundadoMapa>> listarReclamosFundadosInfundados(FiltroReclamosFundadosInfundadosRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarReclamosFundadosInfundados(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<List<ReclamoFundadoInfundadoMapa>> buscarReclamosFundadosInfundados(BuscarReclamoFundadoInfundadoRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarReclamosFundadosInfundados(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
