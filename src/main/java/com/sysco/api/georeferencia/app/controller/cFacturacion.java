package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionAltosConsumidoresRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionVMARequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionAltosConsumidores;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionVMA;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionAltosConsumidoresRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionVMARequest;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.FacturacionService;
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
@RequestMapping("/vektors-facturacion")
@RequiredArgsConstructor
public class cFacturacion {
    private final tokens_webflux tokenFunction;
    private final FacturacionService service;

    @PostMapping("/vma/listar")
    public Mono<ResponseEntity<response_generic<List<FacturacionVMA>>>> listarFacturacionVMA(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroFacturacionVMARequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarFacturacionVMA(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de facturación VMA obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/vma/buscar")
    public Mono<ResponseEntity<response_generic<FacturacionVMA>>> buscarFacturacionVMA(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarFacturacionVMARequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarFacturacionVMA(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Facturación VMA encontrada"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/altos-consumidores/listar")
    public Mono<ResponseEntity<response_generic<List<FacturacionAltosConsumidores>>>> listarFacturacionAltosConsumidores(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroFacturacionAltosConsumidoresRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarFacturacionAltosConsumidores(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de facturación de altos consumidores obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/altos-consumidores/buscar")
    public Mono<ResponseEntity<response_generic<FacturacionAltosConsumidores>>> buscarFacturacionAltosConsumidores(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarFacturacionAltosConsumidoresRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarFacturacionAltosConsumidores(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Facturación de altos consumidores encontrada"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
