package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import com.sysco.api.georeferencia.app.dto.recaudacion.FiltroCoberturaCarRequest;
import com.sysco.api.georeferencia.app.dto.recaudacion.PagoClienteCarMapa;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.recaudacion.ICoberturaCar;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoberturaCarRepositorio extends IGenericRepo implements ICoberturaCar {

    private static final String TODOS = "ALL";

    @Override
    public List<PagoClienteCarMapa> listarPagosPorCar(FiltroCoberturaCarRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_cobertura_pagos_x_car ?,?,?,?,?,?";

            List<PagoClienteCarMapa> pagos = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(PagoClienteCarMapa.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsede() == null ? TODOS : filtro.getCodsede(),
                    filtro.getCodsuc()  == null ? TODOS : filtro.getCodsuc(),
                    filtro.getCar()     == null ? TODOS : filtro.getCar(),
                    filtro.getFechaini(),
                    filtro.getFechafin());

            if (!pagos.isEmpty()) {
                asignarCoordenadas(pagos);
            }

            return pagos;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    private void asignarCoordenadas(List<? extends ConCoordenadas> pagos) throws Exception {
        Long[] codigos = pagos.stream()
                .map(ConCoordenadas::getCodcliente)
                .filter(java.util.Objects::nonNull)
                .map(Number::longValue)
                .distinct()
                .toArray(Long[]::new);

        if (codigos.length == 0) return;

        Map<Long, Double[]> coords = new HashMap<>();

        this.jTemplateGIS().query(
                "SELECT * FROM fn_coordenadas_por_clientes(?)",
                ps -> {
                    Connection c = ps.getConnection();
                    ps.setArray(1, c.createArrayOf("bigint", codigos));
                },
                rs -> {
                    long cod = rs.getLong("codcliente");
                    coords.put(cod,
                            new Double[]{
                                    rs.getObject("lon", Double.class),     rs.getObject("lat", Double.class),
                                    rs.getObject("x_ficha", Double.class), rs.getObject("y_ficha", Double.class)
                            });
                });

        pagos.forEach(p -> {
            if (p.getCodcliente() == null) return;
            long cod = p.getCodcliente().longValue();

            Double[] xy = coords.get(cod);
            if (xy != null) {
                p.setLon(xy[0]);
                p.setLat(xy[1]);
                p.setLonpredio(xy[2]);
                p.setLatpredio(xy[3]);
            }
        });
    }
}
