package com.sysco.api.georeferencia.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaController {
    @GetMapping("/api/test")
    public String test() {
        return "API Georeferencia funcionando correctamente";
    }
}
