package com.sysco.api.georeferencia.app.servicios;

import com.sysco.api.georeferencia.app.dto.lecturas.*;
import com.sysco.api.georeferencia.app.repositorio.LecturasRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zmc.sysco.master.clases.dto.validar_login;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class LecturasService {

    @Autowired
    private LecturasRepositorio repo;

    public Mono<List<MeterReadingSector>> listarLecturas(FiltroLecturasRequest filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.listarLecturas(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<MeterReadingSector> buscarLecturaPorSuministro(String codsuc, String anio, String mes, Integer nroSuministro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.buscarLecturaPorSuministro(codsuc, anio, mes, nroSuministro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<List<ListadoresumenXinspector>> resumentomalectura_xinspectore(Filtroresumenxinspector filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.resumentomalectura_xinspectore(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<List<MeterReadingSector>> detalletomalectura_xinspector(Filtrodetalletomalectura_xinspector filtro, validar_login userLogin) {
        return Mono.fromCallable(() -> this.repo.detalletomalectura_xinspector(filtro, userLogin))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
