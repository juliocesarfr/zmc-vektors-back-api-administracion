package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.reclamos.BuscarReclamoFundadoInfundadoRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.FiltroReclamosFundadosInfundadosRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.ReclamoFundadoInfundadoMapa;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ReclamosFundadosInfundadosService;
import com.zmc.sysco.master.clases.models.genericos.response_generic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/vektors-reclamos")
@RequiredArgsConstructor
public class cReclamosFundadosInfundados {
    private final tokens_webflux tokenFunction;
    private final ReclamosFundadosInfundadosService service;

    @PostMapping("/fundados-infundados/listar")
    public Mono<ResponseEntity<response_generic<List<ReclamoFundadoInfundadoMapa>>>> listarReclamosFundadosInfundados(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroReclamosFundadosInfundadosRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarReclamosFundadosInfundados(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de reclamos fundados e infundados obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/fundados-infundados/buscar")
    public Mono<ResponseEntity<response_generic<List<ReclamoFundadoInfundadoMapa>>>> buscarReclamosFundadosInfundados(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarReclamoFundadoInfundadoRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarReclamosFundadosInfundados(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Reclamos del cliente encontrados"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
