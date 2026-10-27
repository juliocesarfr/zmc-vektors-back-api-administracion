package com.sysco.api.georeferencia.app.controller;

import com.sysco.api.georeferencia.app.dto.recaudacion.FiltroCoberturaCarRequest;
import com.sysco.api.georeferencia.app.dto.recaudacion.PagoClienteCarMapa;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.CoberturaCarService;
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
@RequestMapping("/vektors-recaudacion")
@RequiredArgsConstructor
public class cCoberturaCar {
    private final tokens_webflux tokenFunction;
    private final CoberturaCarService service;

    @PostMapping("/cobertura-car/listar")
    public Mono<ResponseEntity<response_generic<List<PagoClienteCarMapa>>>> listarPagosPorCar(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltroCoberturaCarRequest filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.listarPagosPorCar(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Pagos por CAR obtenidos"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }
}
