package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.vma.BuscarClienteVMARequest;
import com.sysco.api.georeferencia.app.dto.vma.ClientesVMA;
import com.sysco.api.georeferencia.app.dto.vma.FiltroPadronClientesVMARequest;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ClientesVMAService;
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
@RequestMapping("/vektors-vma")
@RequiredArgsConstructor
public class cClientesVMA {
    private final tokens_webflux tokenFunction;
    private final ClientesVMAService service;

    @PostMapping("/padron-clientes/listar")
    public Mono<ResponseEntity<response_generic<List<ClientesVMA>>>> listarPadronClientesNoDomestico(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroPadronClientesVMARequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarPadronClientesNoDomestico(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Lista de padrón VMA obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/padron-clientes/buscar")
    public Mono<ResponseEntity<response_generic<ClientesVMA>>> buscarPadronClientesNoDomestico(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarClienteVMARequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarPadronClientesNoDomestico(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Cliente encontrado en padrón VMA"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
