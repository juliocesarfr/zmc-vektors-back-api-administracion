package com.sysco.api.georeferencia.app.excepciones;

import org.springframework.http.ResponseEntity;
import com.zmc.sysco.master.clases.models.genericos.response_generic;
import reactor.core.publisher.Mono;

public class GenericoExcepcion {
    public static <D> response_generic<D> buildResponse(boolean success, String message, D data) {
        return response_generic.<D>builder()
                .success(success)
                .mensaje(message)
                .data(data)
                .build();
    }

    public static <R> Mono<ResponseEntity<response_generic<R>>> success(R data) {
        return Mono.just(ResponseEntity.ok().body(buildResponse(true, "EXITO", data)));
    }

    public static <R> Mono<ResponseEntity<response_generic<R>>> error(Throwable e) {
        return Mono.just(ResponseEntity.ok().body(buildResponse(false, e.getMessage(), null)));
    }
}