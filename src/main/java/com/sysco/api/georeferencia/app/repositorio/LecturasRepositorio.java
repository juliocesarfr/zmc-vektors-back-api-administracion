package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.lecturas.*;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.lecturas.ILecturas;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class LecturasRepositorio extends IGenericRepo implements ILecturas {

    @Override
    public List<MeterReadingSector> listarLecturas(FiltroLecturasRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_listar_lecturas ?,?,?,?,?,?,?,?,?,?,?";

            List<MeterReadingSector> lecturas = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(MeterReadingSector.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc(),
                    filtro.getCodsede(),
                    filtro.getCodsector()     == null ? "%" : filtro.getCodsector(),
                    filtro.getCodciclo(),
                    filtro.getAnio(),
                    filtro.getMes(),
                    filtro.getEstadolectura() == null ? ""  : filtro.getEstadolectura(),
                    filtro.getConsumoini(),
                    filtro.getConsumofin(),
                    filtro.getTipopromedio());

            if (!lecturas.isEmpty()) {
                asignarCoordenadas(lecturas);
            }

            return lecturas;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage());
        }
    }

    @Override
    public List<ListadoresumenXinspector> resumentomalectura_xinspectore(Filtroresumenxinspector filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_resumentomalectura_xinspectores ?,?,?,?,?,?";

            return this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ListadoresumenXinspector.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodciclo(),
                    filtro.getCodsuc(),
                    filtro.getCodsector()     == null ? "%" : filtro.getCodsector(),
                    filtro.getAnio(),
                    filtro.getMes());

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage());
        }
    }

    @Override
    public List<MeterReadingSector> detalletomalectura_xinspector(Filtrodetalletomalectura_xinspector filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_detalletomalectura_xinspector ?,?,?,?,?,?,?";

            List<MeterReadingSector> lecturas = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(MeterReadingSector.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodciclo(),
                    filtro.getCodsuc(),
                    filtro.getCodsector()     == null ? "%" : filtro.getCodsector(),
                    filtro.getAnio(),
                    filtro.getMes(),
                    filtro.getCodinspector());

            if (!lecturas.isEmpty()) {
                asignarCoordenadas(lecturas);
            }

            return lecturas;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage());
        }
    }
    @Override
    public MeterReadingSector buscarLecturaPorSuministro(String codsuc, String anio, String mes, Integer nroSuministro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_lecturas_nrosuministro ?,?,?,?,?";

            List<MeterReadingSector> lecturas = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(MeterReadingSector.class),
                    userLogin.getCodempdefault(),
                    codsuc,
                    anio,
                    mes,
                    nroSuministro);

            if (!lecturas.isEmpty()) {
                asignarCoordenadas(lecturas);
                return lecturas.get(0);
            }

            return null;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage());
        }
    }

    private void asignarCoordenadas(List<MeterReadingSector> lecturas) throws Exception {
        Long[] codigos = lecturas.stream()
                .map(MeterReadingSector::getCodcliente)
                .filter(java.util.Objects::nonNull)
                .map(Integer::longValue)
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
                    coords.put(rs.getLong("codcliente"),
                            new Double[]{
                                    rs.getObject("lon", Double.class), rs.getObject("lat", Double.class),
                                    rs.getObject("x_ficha", Double.class), rs.getObject("y_ficha", Double.class),
                                    rs.getObject("x_agua", Double.class), rs.getObject("y_agua", Double.class),
                                    rs.getObject("x_desague", Double.class), rs.getObject("y_desague", Double.class),
                                    rs.getObject("x_aco_agua", Double.class), rs.getObject("y_aco_agua", Double.class),
                                    rs.getObject("x_aco_alc", Double.class), rs.getObject("y_aco_alc", Double.class)
                            });
                });

        lecturas.forEach(l -> {
            if (l.getCodcliente() == null) return;
            Double[] xy = coords.get(l.getCodcliente().longValue());

            if (xy != null) {
                l.setLon(xy[0]);
                l.setLat(xy[1]);

                l.setLonpredio(xy[2]);
                l.setLatpredio(xy[3]);

                l.setLonagua(xy[4]);
                l.setLatagua(xy[5]);

                l.setLondesague(xy[6]);
                l.setLatdesague(xy[7]);

                l.setLonacometidaagua(xy[8]);
                l.setLatacometidaagua(xy[9]);

                l.setLonacometidadesague(xy[10]);
                l.setLatacometidadesague(xy[11]);
            }
        });
    }

    private Long parseCodigo(String codcliente) {
        try {
            return codcliente == null ? null : Long.valueOf(codcliente.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}