package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.catastro.BuscarConexionNuevaRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ConexionNuevaInstalada;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroConexionesNuevasRequest;
import com.sysco.api.georeferencia.app.dto.lecturas.ConCoordenadas;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.catastro.IConexionesNuevas;
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
public class ConexionesNuevasRepositorio extends IGenericRepo implements IConexionesNuevas {

    @Override
    public List<ConexionNuevaInstalada> listarConexionesNuevas(FiltroConexionesNuevasRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_padronconexnuevas_016 ?,?,?,?,?,?,?,?,?,?,?";

            List<ConexionNuevaInstalada> conexiones = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ConexionNuevaInstalada.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodciclo()          == null ? "%" : filtro.getCodciclo(),
                    filtro.getCodsuc()            == null ? "%" : filtro.getCodsuc(),
                    filtro.getCodsector()         == null ? "%" : filtro.getCodsector(),
                    filtro.getEstadoservicio()    == null ? "%" : filtro.getEstadoservicio(),
                    filtro.getTiposervicio()      == null ? "%" : filtro.getTiposervicio(),
                    filtro.getCatetar()           == null ? "%" : filtro.getCatetar(),
                    filtro.getFechaini(),
                    filtro.getFechafin(),
                    filtro.getCodtipotramiteing() == null ? "%" : filtro.getCodtipotramiteing(),
                    filtro.getActividad()         == null ? "%" : filtro.getActividad());

            if (!conexiones.isEmpty()) {
                asignarCoordenadas(conexiones);
            }

            return conexiones;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public ConexionNuevaInstalada buscarConexionNueva(BuscarConexionNuevaRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_padronconexnuevas_016 ?,?,?";

            List<ConexionNuevaInstalada> conexiones = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ConexionNuevaInstalada.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc(),
                    new SqlParameterValue(Types.INTEGER, filtro.getCodcliente()));

            if (!conexiones.isEmpty()) {
                asignarCoordenadas(conexiones);
                return conexiones.get(0);
            }

            return null;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    private void asignarCoordenadas(List<? extends ConCoordenadas> conexiones) throws Exception {
        Long[] codigos = conexiones.stream()
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

        conexiones.forEach(l -> {
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
