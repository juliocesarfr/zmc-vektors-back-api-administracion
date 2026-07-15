package com.sysco.api.georeferencia.app.repositorio;


import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.lecturas.FiltroLecturasRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.MeterReadingSector;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.lecturas.ILecturas;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.List;
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

    private void asignarCoordenadas(List<MeterReadingSector> lecturas) throws Exception {
        Long[] codigos = lecturas.stream()
                .map(MeterReadingSector::getCodcliente)
                .filter(java.util.Objects::nonNull)
                .map(Integer::longValue)
                .distinct()
                .toArray(Long[]::new);

        if (codigos.length == 0) return;

        Map<Long, double[]> coords = new HashMap<>();
        this.jTemplateGIS().query(
                "SELECT * FROM fn_coordenadas_por_clientes(?)",
                ps -> {
                    Connection c = ps.getConnection();
                    ps.setArray(1, c.createArrayOf("bigint", codigos));
                },
                rs -> {
                    coords.put(rs.getLong("codcliente"),
                            new double[]{ rs.getDouble("lon"), rs.getDouble("lat") });
                });

        lecturas.forEach(l -> {
            if (l.getCodcliente() == null) return;
            double[] xy = coords.get(l.getCodcliente().longValue());
            if (xy != null) { l.setLon(xy[0]); l.setLat(xy[1]); }
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
