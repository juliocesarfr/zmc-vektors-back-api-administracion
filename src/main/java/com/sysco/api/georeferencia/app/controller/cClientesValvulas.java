package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.valvulas.ClienteValvula;
import com.sysco.api.georeferencia.app.dto.valvulas.FiltroClientesValvulasRequest;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ClientesValvulasService;
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
@RequestMapping("/vektors-gis")
@RequiredArgsConstructor
public class cClientesValvulas {
    private final tokens_webflux tokenFunction;
    private final ClientesValvulasService service;

    @PostMapping("/clientes-valvulas/listar")
    public Mono<ResponseEntity<response_generic<List<ClienteValvula>>>> listarClientesValvulas(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroClientesValvulasRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarClientesValvulas(filtro))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Válvulas cercanas a clientes obtenidas"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
