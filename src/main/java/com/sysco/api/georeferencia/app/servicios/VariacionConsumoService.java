package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.lecturas.FiltroVariacionConsumoRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.VariacionConsumoMapa;
import com.sysco.api.georeferencia.app.repositorio.VariacionConsumoRepositorio;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class VariacionConsumoService {

    @Autowired
    private VariacionConsumoRepositorio repo;

    public Mono<List<VariacionConsumoMapa>> listarVariacionConsumo(FiltroVariacionConsumoRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarVariacionConsumo(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
