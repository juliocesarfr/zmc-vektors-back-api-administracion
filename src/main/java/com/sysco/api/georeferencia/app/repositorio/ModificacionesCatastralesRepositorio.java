package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.catastro.BuscarModificacionCatastralRequest;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroModificacionesCatastralesRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ModificacionCatastral;
import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.catastro.IModificacionesCatastrales;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ModificacionesCatastralesRepositorio extends IGenericRepo implements IModificacionesCatastrales {

    @Override
    public List<ModificacionCatastral> listarModificaciones(FiltroModificacionesCatastralesRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_padronmodificacion_tipodemodificacion ?,?,?,?,?,?";

            List<ModificacionCatastral> modificaciones = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ModificacionCatastral.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc()    == null ? "%" : filtro.getCodsuc(),
                    filtro.getCodsector() == null ? "%" : filtro.getCodsector(),
                    filtro.getAnio(),
                    filtro.getMes(),
                    filtro.getCampo());

            if (!modificaciones.isEmpty()) {
                asignarCoordenadas(modificaciones);
            }

            return modificaciones;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public List<ModificacionCatastral> buscarModificaciones(BuscarModificacionCatastralRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_padronmodificacion_tipodemodificacion ?,?,?,?,?,?,?";

            List<ModificacionCatastral> modificaciones = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ModificacionCatastral.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc()    == null ? "%" : filtro.getCodsuc(),
                    filtro.getCodsector() == null ? "%" : filtro.getCodsector(),
                    filtro.getAnio(),
                    filtro.getMes(),
                    filtro.getCampo(),
                    new SqlParameterValue(Types.INTEGER, filtro.getCodcliente()));

            if (!modificaciones.isEmpty()) {
                asignarCoordenadas(modificaciones);
            }

            return modificaciones;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    private void asignarCoordenadas(List<? extends ConCoordenadas> modificaciones) throws Exception {
        Long[] codigos = modificaciones.stream()
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

        modificaciones.forEach(l -> {
            if (l.getCodcliente() == null) return;
            long cod = l.getCodcliente().longValue();

            Double[] xy = coords.get(cod);
            if (xy != null) {
                l.setLon(xy[0]);
                l.setLat(xy[1]);
                l.setLonpredio(xy[2]);
                l.setLatpredio(xy[3]);
            }
        });
    }
}
