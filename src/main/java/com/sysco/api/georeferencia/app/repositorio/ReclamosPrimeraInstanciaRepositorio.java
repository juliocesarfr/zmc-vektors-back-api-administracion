package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import com.sysco.api.georeferencia.app.dto.reclamos.BuscarReclamoPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.FiltroReclamosPrimeraInstanciaRequest;
import com.sysco.api.georeferencia.app.dto.reclamos.ReclamoPrimeraInstanciaMapa;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.reclamos.IReclamosPrimeraInstancia;
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
public class ReclamosPrimeraInstanciaRepositorio extends IGenericRepo implements IReclamosPrimeraInstancia {

    @Override
    public List<ReclamoPrimeraInstanciaMapa> listarReclamosPrimeraInstancia(FiltroReclamosPrimeraInstanciaRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_reporte_primera_instancia ?,?,?,?,?,?,?,?";

            List<ReclamoPrimeraInstanciaMapa> reclamos = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ReclamoPrimeraInstanciaMapa.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc()      == null ? "%" : filtro.getCodsuc(),
                    filtro.getCodsede()     == null ? "%" : filtro.getCodsede(),
                    filtro.getFechaini(),
                    filtro.getFechafin(),
                    filtro.getCodreclamo()  == null ? "%" : filtro.getCodreclamo(),
                    filtro.getTiporeclamo() == null ? "%" : filtro.getTiporeclamo(),
                    filtro.getCodoficina()  == null ? "%" : filtro.getCodoficina());

            if (!reclamos.isEmpty()) {
                asignarCoordenadas(reclamos);
            }

            return reclamos;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    // Un cliente puede tener varios reclamos en el rango de fechas: se devuelven todos.
    @Override
    public List<ReclamoPrimeraInstanciaMapa> buscarReclamosPrimeraInstancia(BuscarReclamoPrimeraInstanciaRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_reporte_primera_instancia ?,?,?,?,?,?";

            List<ReclamoPrimeraInstanciaMapa> reclamos = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ReclamoPrimeraInstanciaMapa.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc()  == null ? "%" : filtro.getCodsuc(),
                    filtro.getCodsede() == null ? "%" : filtro.getCodsede(),
                    filtro.getFechaini(),
                    filtro.getFechafin(),
                    new SqlParameterValue(Types.INTEGER, filtro.getCodcliente()));

            if (!reclamos.isEmpty()) {
                asignarCoordenadas(reclamos);
            }

            return reclamos;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    private void asignarCoordenadas(List<? extends ConCoordenadas> reclamos) throws Exception {
        Long[] codigos = reclamos.stream()
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

        reclamos.forEach(l -> {
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
