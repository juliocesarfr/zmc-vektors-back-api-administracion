package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import com.sysco.api.georeferencia.app.dto.lecturas.FiltroVariacionConsumoRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.VariacionConsumoMapa;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.lecturas.IVariacionConsumo;
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
public class VariacionConsumoRepositorio extends IGenericRepo implements IVariacionConsumo {

    private static final String TODOS = "ALL";

    @Override
    public List<VariacionConsumoMapa> listarVariacionConsumo(FiltroVariacionConsumoRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_variacion_consumo ?,?,?,?,?,?,?,?,?,?,?,?";

            // El consumo va con su tipo: jTDS manda un null sin tipo como varchar y Sybase no lo convierte a int.
            List<VariacionConsumoMapa> variaciones = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(VariacionConsumoMapa.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodciclo(),
                    filtro.getCodsuc(),
                    filtro.getCodsede()   == null ? TODOS : filtro.getCodsede(),
                    filtro.getCodsector() == null ? TODOS : filtro.getCodsector(),
                    filtro.getAnio(),
                    filtro.getMes1(),
                    filtro.getMes2(),
                    filtro.getEstadolectura() == null ? "" : filtro.getEstadolectura(),
                    new SqlParameterValue(Types.INTEGER, filtro.getConsumoini()),
                    new SqlParameterValue(Types.INTEGER, filtro.getConsumofin()),
                    filtro.getTipopromedio() == null ? "" : filtro.getTipopromedio());

            if (!variaciones.isEmpty()) {
                asignarCoordenadas(variaciones);
            }

            return variaciones;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    private void asignarCoordenadas(List<? extends ConCoordenadas> variaciones) throws Exception {
        Long[] codigos = variaciones.stream()
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
                            new Double[]{ rs.getObject("lon", Double.class), rs.getObject("lat", Double.class) });
                });

        variaciones.forEach(v -> {
            if (v.getCodcliente() == null) return;
            long cod = v.getCodcliente().longValue();

            Double[] xy = coords.get(cod);
            if (xy != null) {
                v.setLon(redondear(xy[0]));
                v.setLat(redondear(xy[1]));
            }
        });
    }

    // Seis decimales son unos 10 cm en el terreno; los 15 que trae PostGIS solo engordan el JSON.
    private static Double redondear(Double grados) {
        return grados == null ? null : Math.round(grados * 1_000_000d) / 1_000_000d;
    }
}
