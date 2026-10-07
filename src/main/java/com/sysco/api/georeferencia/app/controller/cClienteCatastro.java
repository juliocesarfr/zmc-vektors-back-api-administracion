package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteCatastroRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteCatastroResponse;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ClienteCatastroService;
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
public class cClienteCatastro {
    private final tokens_webflux tokenFunction;
    private final ClienteCatastroService service;

    @PostMapping("/cliente/buscar")
    public Mono<ResponseEntity<response_generic<List<ClienteCatastroResponse>>>> buscarClienteCatastro(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarClienteCatastroRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarClienteCatastro(request, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Cliente catastro encontrado"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
