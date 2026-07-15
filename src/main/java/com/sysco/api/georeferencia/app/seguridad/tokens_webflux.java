package com.sysco.api.georeferencia.app.seguridad;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmc.sysco.master.clases.config.Cifrador;
import com.zmc.sysco.master.clases.seguridad.tokens;
import com.zmc.sysco.master.clases.dto.validar_login;
import java.util.Base64;

@Service
@Slf4j
public class tokens_webflux {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String tokens_string = null;
    private tokens tk;
    private Cifrador cifrar;

    public tokens_webflux() {
        this.tk 	= new tokens();
        this.cifrar = new Cifrador();
    }

    public Mono<validar_login> EncodeToken(validar_login v, Integer op){
        return Mono.fromCallable(() -> this.tk.encodeBodyToken_String(v, op))
                .flatMap(this::extractBase64EncodedBody)
                .flatMap(this::decodeBase64Body)
                .flatMap(this::readJsonTree)
                .flatMap(this::buildUserLogin);
    }

    public Mono<validar_login> DecodeToken(String jwtToken) {
        return Mono.just(jwtToken)
                .flatMap(this::extractBase64EncodedBody)
                .flatMap(this::decodeBase64Body)
                .flatMap(this::readJsonTree)
                .flatMap(this::buildUserLogin);
    }

    public Mono<String> DecodeTokenB(String jwtToken) {
        return Mono.just(jwtToken)
                .flatMap(this::extractBase64EncodedBody)
                .flatMap(this::decodeBase64Body);
    }

    private Mono<String> extractBase64EncodedBody(String jwtToken) {
        this.tokens_string = jwtToken;
        return Mono.just(jwtToken).flatMap(token -> Mono.just(token.split("\\.")[1]))
                .onErrorResume(e -> Mono.error(new IllegalStateException("Formato de token inválido")));
    }

    public Mono<String> decodeBase64Body(String base64EncodedBody) {
        return Mono.just(base64EncodedBody)
                .map(encodedBody -> new String(Base64.getDecoder().decode(encodedBody)));
    }


    public Mono<String[]> convert_split(String cadena,String separador){
        return Mono.just(cadena)
                .map(cadenaResult -> cadenaResult.split(separador));
    }

    private Mono<JsonNode> readJsonTree(String jsonBody) {
        log.info("Response status 01: {}", jsonBody);
        try {
            return Mono.just(this.objectMapper.readTree(jsonBody));
        } catch (JsonProcessingException e) {
            return Mono.error(new IllegalStateException("Error al decodificar el token de autenticación"));
        }
    }

    private Mono<validar_login> buildUserLogin(JsonNode json) {
        return Mono.just(
                validar_login.builder()
                        .mensaje("EXITO")
                        .token(this.tokens_string)
                        .apellidopa(json.get("apellidopa") == null ? "" : json.get("apellidopa").asText())
                        .apellidoma(json.get("apellidoma") == null ? "" : json.get("apellidoma").asText())
                        .nombre(json.get("usuario") == null ? "" : json.get("usuario").asText())
                        .codempdefault(json.get("codemp") == null ? "" : json.get("codemp").asText())
                        .codsucdefault(json.get("codsuc") == null ? "" : json.get("codsuc").asText())
                        .codsededefault(json.get("codsede") == null ? "" : json.get("codsede").asText())
                        .empresa(json.get("empresa") == null ? "" : json.get("empresa").asText())
                        .sucursal(json.get("sucursal") == null ? "" : json.get("sucursal").asText())
                        .sedeoperacional(json.get("sedeoperacional") == null ? "" : json.get("sedeoperacional").asText())
                        .codusu(json.get("codusu") == null ? "" : json.get("codusu").asText())
                        .password(json.get("password") == null ? "" : cifrar.descifra(json.get("password").asText()))
                        .foto("")
                        .expiracion(json.get("expiracion") == null ? 0 : json.get("expiracion").asLong())
                        .ipservidor(json.get("ipservidor") == null ? "" : cifrar.descifra(json.get("ipservidor").asText()))
                        .nombre_bd(json.get("nombre_bd") == null ? "" : cifrar.descifra(json.get("nombre_bd").asText()))
                        .puerto(json.get("puerto") == null ? "" : cifrar.descifra(json.get("puerto").asText()))
                        .sucdef(json.get("sucdef") == null ? "" : json.get("sucdef").asText())
                        .build()
        );
    }
}
