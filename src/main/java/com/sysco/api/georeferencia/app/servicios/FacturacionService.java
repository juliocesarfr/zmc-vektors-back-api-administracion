package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionAltosConsumidoresRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionVMARequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionAltosConsumidores;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionVMA;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionAltosConsumidoresRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionVMARequest;
import com.sysco.api.georeferencia.app.repositorio.FacturacionRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zmc.sysco.master.clases.dto.validar_login;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class FacturacionService {

    @Autowired
    private FacturacionRepositorio repo;

    public Mono<List<FacturacionVMA>> listarFacturacionVMA(FiltroFacturacionVMARequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarFacturacionVMA(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<FacturacionVMA> buscarFacturacionVMA(BuscarFacturacionVMARequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarFacturacionVMA(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<List<FacturacionAltosConsumidores>> listarFacturacionAltosConsumidores(FiltroFacturacionAltosConsumidoresRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarFacturacionAltosConsumidores(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<FacturacionAltosConsumidores> buscarFacturacionAltosConsumidores(BuscarFacturacionAltosConsumidoresRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarFacturacionAltosConsumidores(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
