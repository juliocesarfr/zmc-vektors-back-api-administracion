package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.lecturas.BuscarLecturaRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.FiltroLecturasRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.MeterReadingSector;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.LecturasService;
import com.zmc.sysco.master.clases.models.genericos.response_generic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/vektors")
@RequiredArgsConstructor
public class cLecturas {
    private final tokens_webflux tokenFunction;
    private final LecturasService service;

    @PostMapping("/lecturas/listar")
    public Mono<ResponseEntity<response_generic<List<MeterReadingSector>>>> listarLecturas(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroLecturasRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarLecturas(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/lecturas/buscar")
    public Mono<ResponseEntity<response_generic<MeterReadingSector>>> buscarLectura(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarLecturaRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarLecturaPorSuministro(
                        request.getCodsuc(),
                        request.getAnio(),
                        request.getMes(),
                        request.getNroSuministro(),
                        userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lectura encontrada"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
