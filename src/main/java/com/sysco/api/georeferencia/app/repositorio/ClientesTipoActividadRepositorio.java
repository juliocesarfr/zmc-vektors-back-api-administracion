package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteActividadRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteTipoActividad;
import com.sysco.api.georeferencia.app.dto.catastro.FiltroPadronClientesTipoActividadRequest;


import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.catastro.IClientesTipoActividad;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientesTipoActividadRepositorio extends IGenericRepo implements IClientesTipoActividad {

    @Override
    public List<ClienteTipoActividad> listarPadronActividad(FiltroPadronClientesTipoActividadRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_reporte_padron_clientes_tipo_actividad ?,?,?,?,?,?,?,?,?,?";

            List<ClienteTipoActividad> clientes = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ClienteTipoActividad.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodciclo()     == null ? "%" : filtro.getCodciclo(),
                    filtro.getCodsuc()       == null ? "%" : filtro.getCodsuc(),
                    filtro.getCodsector()    == null ? "%" : filtro.getCodsector(),
                    filtro.getEstservicio()  == null ? "%" : filtro.getEstservicio(),
                    filtro.getTiposervicio() == null ? "%" : filtro.getTiposervicio(),
                    filtro.getCatetar()      == null ? "%" : filtro.getCatetar(),
                    filtro.getUrbani()       == null ? "%" : filtro.getUrbani(),
                    filtro.getTipousuario()  == null ? "%" : filtro.getTipousuario(),
                    filtro.getActividad()    == null ? "%" : filtro.getActividad());

            if (!clientes.isEmpty()) {
                asignarCoordenadas(clientes);
            }

            return clientes;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public ClienteTipoActividad buscarClienteActividad(BuscarClienteActividadRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_reporte_padron_clientes_tipo_actividad ?,?,?";

            List<ClienteTipoActividad> clientes = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ClienteTipoActividad.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc(),
                    filtro.getCodcliente());

            if (!clientes.isEmpty()) {
                asignarCoordenadas(clientes);
                return clientes.get(0);
            }

            
            return null;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    private void asignarCoordenadas(List<ClienteTipoActividad> clientes) throws Exception {
        Long[] codigos = clientes.stream()
                .map(ClienteTipoActividad::getCodcliente)
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
                    long cod = rs.getLong("codcliente");
                    coords.put(cod,
                            new Double[]{
                                    rs.getObject("lon", Double.class),        rs.getObject("lat", Double.class),
                                    rs.getObject("x_ficha", Double.class),    rs.getObject("y_ficha", Double.class),
                                    rs.getObject("x_agua", Double.class),     rs.getObject("y_agua", Double.class),
                                    rs.getObject("x_desague", Double.class),  rs.getObject("y_desague", Double.class),
                                    rs.getObject("x_aco_agua", Double.class), rs.getObject("y_aco_agua", Double.class),
                                    rs.getObject("x_aco_alc", Double.class),  rs.getObject("y_aco_alc", Double.class)
                            });
                });

        clientes.forEach(l -> {
            if (l.getCodcliente() == null) return;
            long cod = l.getCodcliente().longValue();

            Double[] xy = coords.get(cod);
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
}