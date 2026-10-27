package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.lecturas.FiltroVariacionConsumoRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.VariacionConsumoMapa;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.VariacionConsumoService;
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
public class cVariacionConsumo {
    private final tokens_webflux tokenFunction;
    private final VariacionConsumoService service;

    @PostMapping("/variacion-consumo/listar")
    public Mono<ResponseEntity<response_generic<List<VariacionConsumoMapa>>>> listarVariacionConsumo(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroVariacionConsumoRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarVariacionConsumo(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Variación de consumo obtenida"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
