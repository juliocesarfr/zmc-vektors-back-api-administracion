package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.lecturas.*;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.lecturas.ILecturas;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
            throw new RepositorioExcepcion(ex.getMessage(), ex);
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
            throw new RepositorioExcepcion(ex.getMessage(), ex);
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
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public List<MeterReadingSector> buscarLectura(BuscarLecturaRequest request, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_buscar_lecturas_nrosuministro ?,?,?,?,?,?";

            int op = (request.getOp() != null) ? request.getOp() : 1;
            String valor = (request.getValor() != null && !request.getValor().trim().isEmpty())
                    ? request.getValor().trim()
                    : (request.getNroSuministro() != null ? String.valueOf(request.getNroSuministro()) : "");

            List<MeterReadingSector> lecturas = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(MeterReadingSector.class),
                    op,
                    userLogin.getCodempdefault(),
                    request.getCodsuc(),
                    request.getAnio(),
                    request.getMes(),
                    valor);

            if (!lecturas.isEmpty()) {
                asignarCoordenadas(lecturas);
            }

            return lecturas;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public MeterReadingSector buscarLecturaPorSuministro(String codsuc, String anio, String mes, Integer nroSuministro, validar_login userLogin) {
        BuscarLecturaRequest request = BuscarLecturaRequest.builder()
                .op(1)
                .codsuc(codsuc)
                .anio(anio)
                .mes(mes)
                .nroSuministro(nroSuministro)
                .valor(nroSuministro != null ? String.valueOf(nroSuministro) : null)
                .build();
        List<MeterReadingSector> list = buscarLectura(request, userLogin);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
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
                "SELECT * FROM public.fn_coordenadas_por_clientes(?::bigint[])",
                ps -> {
                    Connection c = ps.getConnection();
                    ps.setArray(1, c.createArrayOf("bigint", codigos));
                },
                new org.springframework.jdbc.core.RowCallbackHandler() {
                    private boolean metaDataLoaded = false;
                    private boolean esFuncionNueva = false;

                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        // 1. Revisamos las columnas solo en la primera fila
                        if (!metaDataLoaded) {
                            ResultSetMetaData rsmd = rs.getMetaData();
                            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                                if (rsmd.getColumnName(i).equalsIgnoreCase("lon_cliente")) {
                                    esFuncionNueva = true;
                                    break;
                                }
                            }
                            metaDataLoaded = true;
                        }

                        long cod = rs.getLong("codcliente");

                        // 2. Extraemos los datos según la versión detectada
                        if (esFuncionNueva) {
                            coords.put(cod, new Double[]{
                                    rs.getObject("lon_cliente", Double.class), rs.getObject("lat_cliente", Double.class),
                                    null, null, // x_ficha, y_ficha no existen en la nueva
                                    rs.getObject("lon_agua", Double.class),    rs.getObject("lat_agua", Double.class),
                                    rs.getObject("lon_desague", Double.class), rs.getObject("lat_desague", Double.class),
                                    null, null, // acometidas agua
                                    null, null  // acometidas alcantarillado
                            });
                        } else {
                            coords.put(cod, new Double[]{
                                    rs.getObject("lon", Double.class),        rs.getObject("lat", Double.class),
                                    rs.getObject("x_ficha", Double.class),    rs.getObject("y_ficha", Double.class),
                                    rs.getObject("x_agua", Double.class),     rs.getObject("y_agua", Double.class),
                                    rs.getObject("x_desague", Double.class),  rs.getObject("y_desague", Double.class),
                                    rs.getObject("x_aco_agua", Double.class), rs.getObject("y_aco_agua", Double.class),
                                    rs.getObject("x_aco_alc", Double.class),  rs.getObject("y_aco_alc", Double.class)
                            });
                        }
                    }
                });

        lecturas.forEach(l -> {
            if (l.getCodcliente() == null) return;
            long cod = l.getCodcliente().longValue();

            Double[] xy = coords.get(cod);
            if (xy != null) {
                l.setLon(xy[0]);                   l.setLat(xy[1]);
                l.setLonpredio(xy[2]);             l.setLatpredio(xy[3]);
                l.setLonagua(xy[4]);               l.setLatagua(xy[5]);
                l.setLondesague(xy[6]);            l.setLatdesague(xy[7]);
                l.setLonacometidaagua(xy[8]);      l.setLatacometidaagua(xy[9]);
                l.setLonacometidadesague(xy[10]);  l.setLatacometidadesague(xy[11]);
            }
        });
    }
}