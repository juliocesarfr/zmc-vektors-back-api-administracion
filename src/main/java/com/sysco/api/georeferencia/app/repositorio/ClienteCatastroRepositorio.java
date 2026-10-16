package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteCatastroRequest;
import com.sysco.api.georeferencia.app.dto.catastro.BusquedaAvanzadaClienteResponse;
import com.sysco.api.georeferencia.app.dto.catastro.BusquedaAvanzadaClientesRequest;
import com.sysco.api.georeferencia.app.dto.catastro.ClienteCatastroResponse;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.catastro.IClienteCatastro;
import com.zmc.sysco.master.clases.dto.validar_login;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteCatastroRepositorio extends IGenericRepo implements IClienteCatastro {

    @Override
    public List<ClienteCatastroResponse> buscarClienteCatastro(BuscarClienteCatastroRequest request, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_visualizar_datos_clientes_catastro ?,?,?";

            return this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(ClienteCatastroResponse.class),
                    userLogin.getCodempdefault(),
                    request.getCodsuc(),
                    request.getCodcliente());

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    @Override
    public List<BusquedaAvanzadaClienteResponse> busquedaAvanzadaClientes(BusquedaAvanzadaClientesRequest request, validar_login userLogin) {
        try {
            String query = "exec dbo.usp_vektors_busqueda_avanzada_clientes ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

            String codemp = userLogin.getCodempdefault() != null ? userLogin.getCodempdefault() : "001";
            String codsuc = val(request.getCodsuc());
            int inicio = request.getInicio() != null ? request.getInicio() : 0;
            int nropagina = request.getNropagina() != null ? request.getNropagina() : 99999;
            String codcalle = val(request.getCodcalle());
            String codcliente = val(request.getCodcliente());
            String nromedidor = val(request.getNromedidor());
            String codurbaso = val(request.getCodurbaso());
            String tipodocident = val(request.getTipodocident());
            String nrodocumento = val(request.getNrodocumento());
            String propietario = val(request.getPropietario());
            int letrainicio = request.getLetrainicio() != null ? request.getLetrainicio() : 1;
            String nrorecibo = val(request.getNrorecibo());
            String nropedidoqr = val(request.getNropedidoqr());
            String nropedidovisa = val(request.getNropedidovisa());
            String codsector = val(request.getCodsector());
            String codmza = val(request.getCodmza());
            String nrolote = val(request.getNrolote());
            String nrosublote = val(request.getNrosublote());
            String codcatastral = val(request.getCodcatastral());

            List<BusquedaAvanzadaClienteResponse> clientes = this.jTemplateSIINCO(userLogin).query(query,
                    new BeanPropertyRowMapper<>(BusquedaAvanzadaClienteResponse.class),
                    codemp,
                    codsuc,
                    inicio,
                    nropagina,
                    codcalle,
                    codcliente,
                    nromedidor,
                    codurbaso,
                    tipodocident,
                    nrodocumento,
                    propietario,
                    letrainicio,
                    nrorecibo,
                    nropedidoqr,
                    nropedidovisa,
                    codsector,
                    codmza,
                    nrolote,
                    nrosublote,
                    codcatastral);

            if (clientes != null && !clientes.isEmpty()) {
                if (!"ALL".equals(codcatastral)) {
                    final String buscado = codcatastral.replaceAll("[-\\s]", "");
                    java.util.Optional<BusquedaAvanzadaClienteResponse> exacto = clientes.stream()
                            .filter(c -> c.getCodcatastral() != null &&
                                    c.getCodcatastral().replaceAll("[-\\s]", "").equalsIgnoreCase(buscado))
                            .findFirst();

                    if (exacto.isPresent()) {
                        clientes = new java.util.ArrayList<>(java.util.Collections.singletonList(exacto.get()));
                    } else {
                        clientes = new java.util.ArrayList<>(java.util.Collections.singletonList(clientes.get(0)));
                    }
                }
                asignarCoordenadas(clientes);
            }

            return clientes;

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }

    private String val(String str) {
        return (str != null && !str.trim().isEmpty()) ? str.trim() : "ALL";
    }

    private void asignarCoordenadas(List<BusquedaAvanzadaClienteResponse> clientes) throws Exception {
        Long[] codigos = clientes.stream()
                .map(BusquedaAvanzadaClienteResponse::getCodcliente)
                .filter(java.util.Objects::nonNull)
                .map(Integer::longValue)
                .distinct()
                .toArray(Long[]::new);

        if (codigos.length == 0) return;

        java.util.Map<Long, Double[]> coords = new java.util.HashMap<>();

        this.jTemplateGIS().query(
                "SELECT * FROM public.fn_coordenadas_por_clientes(?::bigint[])",
                ps -> {
                    java.sql.Connection c = ps.getConnection();
                    ps.setArray(1, c.createArrayOf("bigint", codigos));
                },
                new org.springframework.jdbc.core.RowCallbackHandler() {
                    private boolean metaDataLoaded = false;
                    private boolean esFuncionNueva = false;

                    @Override
                    public void processRow(java.sql.ResultSet rs) throws java.sql.SQLException {
                        if (!metaDataLoaded) {
                            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                                if (rsmd.getColumnName(i).equalsIgnoreCase("lon_cliente")) {
                                    esFuncionNueva = true;
                                    break;
                                }
                            }
                            metaDataLoaded = true;
                        }

                        long cod = rs.getLong("codcliente");

                        if (esFuncionNueva) {
                            coords.put(cod, new Double[]{
                                    rs.getObject("lon_cliente", Double.class), rs.getObject("lat_cliente", Double.class),
                                    null, null,
                                    rs.getObject("lon_agua", Double.class),    rs.getObject("lat_agua", Double.class),
                                    rs.getObject("lon_desague", Double.class), rs.getObject("lat_desague", Double.class),
                                    null, null,
                                    null, null
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

        clientes.forEach(l -> {
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
