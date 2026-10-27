package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.recaudacion.FiltroCoberturaCarRequest;
import com.sysco.api.georeferencia.app.dto.recaudacion.PagoClienteCarMapa;
import com.sysco.api.georeferencia.app.repositorio.CoberturaCarRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class CoberturaCarService {

    @Autowired
    private CoberturaCarRepositorio repo;

    public Mono<List<PagoClienteCarMapa>> listarPagosPorCar(FiltroCoberturaCarRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarPagosPorCar(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
