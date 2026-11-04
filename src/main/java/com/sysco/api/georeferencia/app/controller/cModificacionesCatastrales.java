package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarModificacionCatastralRequest;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroModificacionesCatastralesRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ModificacionCatastral;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ModificacionesCatastralesService;
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
public class cModificacionesCatastrales {
    private final tokens_webflux tokenFunction;
    private final ModificacionesCatastralesService service;

    @PostMapping("/modificaciones/listar")
    public Mono<ResponseEntity<response_generic<List<ModificacionCatastral>>>> listarModificaciones(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroModificacionesCatastralesRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarModificaciones(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de modificaciones catastrales obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/modificaciones/buscar")
    public Mono<ResponseEntity<response_generic<List<ModificacionCatastral>>>> buscarModificaciones(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarModificacionCatastralRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarModificaciones(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Modificaciones del cliente encontradas"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
