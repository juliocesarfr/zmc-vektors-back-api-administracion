package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.reclamos.BuscarReclamoPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.FiltroReclamosPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.ReclamoPrimeraInstanciaMapa;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ReclamosPrimeraInstanciaService;
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
public class cReclamosPrimeraInstancia {
    private final tokens_webflux tokenFunction;
    private final ReclamosPrimeraInstanciaService service;

    @PostMapping("/primera-instancia/listar")
    public Mono<ResponseEntity<response_generic<List<ReclamoPrimeraInstanciaMapa>>>> listarReclamosPrimeraInstancia(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroReclamosPrimeraInstanciaRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarReclamosPrimeraInstancia(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de reclamos de primera instancia obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/primera-instancia/buscar")
    public Mono<ResponseEntity<response_generic<List<ReclamoPrimeraInstanciaMapa>>>> buscarReclamosPrimeraInstancia(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarReclamoPrimeraInstanciaRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarReclamosPrimeraInstancia(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Reclamos del cliente encontrados"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
