package com.sysco.api.georeferencia.app.controller;
import com.sysco.api.georeferencia.app.dto.cobranza.BuscarPreCorteRequest;
import com.sysco.api.georeferencia.app.dto.cobranza.ClientesProgramadosPreCorte;
import com.sysco.api.georeferencia.app.dto.cobranza.FiltrarProgramaPrecorte;
import com.sysco.api.georeferencia.app.excepciones.GenericoExcepcion;
import com.sysco.api.georeferencia.app.seguridad.tokens_webflux;
import com.sysco.api.georeferencia.app.servicios.ClienteProgramaCoreService;
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
@RequestMapping("/vektors-core")
@RequiredArgsConstructor
public class cClienteProgramaCore {
    private final tokens_webflux tokenFunction;
    private final ClienteProgramaCoreService service;

    @PostMapping("/programas/listar")
    public Mono<ResponseEntity<response_generic<List<ClientesProgramadosPreCorte>>>> listarclientesconprogrmadosCore(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody FiltrarProgramaPrecorte filtro
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.clientesconprogrmadosCore(filtro, userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

    @PostMapping("/programas/buscar")
    public Mono<ResponseEntity<response_generic<ClientesProgramadosPreCorte>>> buscarPreCortePorCliente(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody BuscarPreCorteRequest request
    ) {
        return this.tokenFunction.DecodeToken(authHeader)
                .flatMap(userLogin -> this.service.buscarPreCortePorCliente(
                        request.getCodsuc(),
                        request.getCodcliente(),
                        request.getNroPrecorte(),
                        userLogin))
                .flatMap(GenericoExcepcion::success)
                .doOnSuccess(response -> log.info("Operación exitosa: Precorte encontrado"))
                .doOnError(error -> log.error("Error en Operación: {}", error.getMessage()))
                .onErrorResume(GenericoExcepcion::error);
    }

}
