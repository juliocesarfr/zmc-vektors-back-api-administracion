package com.sysco.api.georeferencia.app.controller;


import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteActividadRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteTipoActividad;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroPadronClientesTipoActividadRequest;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;

import com.sysco.api.georeferencia.app.servicios.ClientesTipoActividadService;
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
public class cClientesTipoActividad {
    private final tokens_webflux tokenFunction;
    private final ClientesTipoActividadService service;

    @PostMapping("/clientes/listar")
    public Mono<ResponseEntity<response_generic<List<ClienteTipoActividad>>>> listarPadronActividad(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroPadronClientesTipoActividadRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarPadronActividad(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de padrón obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/clientes/buscar")
    public Mono<ResponseEntity<response_generic<ClienteTipoActividad>>> buscarClienteActividad(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarClienteActividadRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarClienteActividad(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Cliente encontrado en padrón"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}