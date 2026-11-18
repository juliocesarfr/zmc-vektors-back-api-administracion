package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.lecturas.BuscarVariacionPromedioRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.FiltroVariacionPromedioRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.VariacionPromedioMapa;
import com.sysco.api.georeferencia.app.repositorio.VariacionPromedioRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class VariacionPromedioService {

    @Autowired
    private VariacionPromedioRepositorio repo;

    public Mono<List<VariacionPromedioMapa>> listarVariacionPromedio(FiltroVariacionPromedioRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarVariacionPromedio(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<VariacionPromedioMapa> buscarVariacionPromedio(BuscarVariacionPromedioRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarVariacionPromedio(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
