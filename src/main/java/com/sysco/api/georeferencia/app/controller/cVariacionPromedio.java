package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.lecturas.BuscarVariacionPromedioRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.FiltroVariacionPromedioRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.VariacionPromedioMapa;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.VariacionPromedioService;
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
@RequestMapping("/vektors-medicion")
@RequiredArgsConstructor
public class cVariacionPromedio {
    private final tokens_webflux tokenFunction;
    private final VariacionPromedioService service;

    @PostMapping("/variacion-promedio/listar")
    public Mono<ResponseEntity<response_generic<List<VariacionPromedioMapa>>>> listarVariacionPromedio(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroVariacionPromedioRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarVariacionPromedio(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Variación respecto al promedio obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/variacion-promedio/buscar")
    public Mono<ResponseEntity<response_generic<VariacionPromedioMapa>>> buscarVariacionPromedio(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarVariacionPromedioRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarVariacionPromedio(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Variación del cliente encontrada"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
