package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionAltosConsumidoresRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.BuscarFacturacionVMARequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionAltosConsumidores;
import com.sysco.api.georeferencia.app.dto.facturacion.FacturacionVMA;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionAltosConsumidoresRequest;
import com.sysco.api.georeferencia.app.dto.facturacion.FiltroFacturacionVMARequest;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.facturacion.IFacturacionAltosConsumidores;
import com.sysco.api.georeferencia.app.interfaces.facturacion.IFacturacionVMA;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Repository
public class FacturacionRepositorio extends IGenericRepo implements IFacturacionVMA, IFacturacionAltosConsumidores {

    @Override
    public List<FacturacionVMA> listarFacturacionVMA(FiltroFacturacionVMARequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_clientes_facturacion_vma ?,?,?,?";

            List<FacturacionVMA> clientes = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(FacturacionVMA.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc() == null ? "%" : filtro.getCodsuc(),
                    filtro.getAnio(),
                    filtro.getMes());

            if (!clientes.isEmpty()) {
                asignarCoordenadasVMA(clientes);
            }

            return clientes;
        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public FacturacionVMA buscarFacturacionVMA(BuscarFacturacionVMARequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_clientes_facturacion_vma ?,?,?,?,?";

            List<FacturacionVMA> clientes = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(FacturacionVMA.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc(),
                    filtro.getAnio(),
                    filtro.getMes(),
                    filtro.getCodcliente());

            if (!clientes.isEmpty()) {
                asignarCoordenadasVMA(clientes);
                return clientes.get(0);
            }

            return null;
        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }


    @Override
    public List<FacturacionAltosConsumidores> listarFacturacionAltosConsumidores(FiltroFacturacionAltosConsumidoresRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_reporte_padronclientes_altocon2 ?,?,?,?,?,?,?,?,?";

            List<FacturacionAltosConsumidores> clientes = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(FacturacionAltosConsumidores.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodciclo() == null ? "%" : filtro.getCodciclo(),
                    filtro.getCodsuc() == null ? "%" : filtro.getCodsuc(),
                    filtro.getCodsector() == null ? "%" : filtro.getCodsector(),
                    filtro.getCodest() == null ? "%" : filtro.getCodest(),
                    filtro.getAnio(),
                    filtro.getMes(),
                    filtro.getTipserv() == null ? "%" : filtro.getTipserv(),
                    filtro.getCatet() == null ? "%" : filtro.getCatet());

            if (!clientes.isEmpty()) {
                asignarCoordenadasAltosConsumidores(clientes);
            }

            return clientes;
        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public FacturacionAltosConsumidores buscarFacturacionAltosConsumidores(BuscarFacturacionAltosConsumidoresRequest filtro, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_padronclientes_altocon2 ?,?,?,?,?";

            List<FacturacionAltosConsumidores> clientes = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(FacturacionAltosConsumidores.class),
                    userLogin.getCodempdefault(),
                    filtro.getCodsuc(),
                    filtro.getAnio(),
                    filtro.getMes(),
                    filtro.getCodcliente());

            if (!clientes.isEmpty()) {
                asignarCoordenadasAltosConsumidores(clientes);
                return clientes.get(0);
            }

            return null;
        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    // --- HELPER METODOS COORDENADAS ---

    private void asignarCoordenadasVMA(List<FacturacionVMA> clientes) throws Exception {
        Long[] codigos = clientes.stream()
                .map(FacturacionVMA::getCodcliente)
                .filter(java.util.Objects::nonNull)
                .map(Integer::longValue)
                .distinct()
                .toArray(Long[]::new);

        if (codigos.length == 0) return;

        Map<Long, Double[]> coords = getCoordsMap(codigos);

        clientes.forEach(l -> {
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

    private void asignarCoordenadasAltosConsumidores(List<FacturacionAltosConsumidores> clientes) throws Exception {
        Long[] codigos = clientes.stream()
                .map(FacturacionAltosConsumidores::getCodcliente)
                .filter(java.util.Objects::nonNull)
                .map(Integer::longValue)
                .distinct()
                .toArray(Long[]::new);

        if (codigos.length == 0) return;

        Map<Long, Double[]> coords = getCoordsMap(codigos);

        clientes.forEach(l -> {
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

    private Map<Long, Double[]> getCoordsMap(Long[] codigos) throws Exception {
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
        return coords;
    }
}
