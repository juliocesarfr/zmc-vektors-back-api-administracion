package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarConexionNuevaRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ConexionNuevaInstalada;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroConexionesNuevasRequest;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ConexionesNuevasService;
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
@RequestMapping("/vektors-catastro")
@RequiredArgsConstructor
public class cConexionesNuevas {
    private final tokens_webflux tokenFunction;
    private final ConexionesNuevasService service;

    @PostMapping("/conexiones-nuevas/listar")
    public Mono<ResponseEntity<response_generic<List<ConexionNuevaInstalada>>>> listarConexionesNuevas(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroConexionesNuevasRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarConexionesNuevas(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de conexiones nuevas obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/conexiones-nuevas/buscar")
    public Mono<ResponseEntity<response_generic<ConexionNuevaInstalada>>> buscarConexionNueva(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarConexionNuevaRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarConexionNueva(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Conexión nueva encontrada"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
