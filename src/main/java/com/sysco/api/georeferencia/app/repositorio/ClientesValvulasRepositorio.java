package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.valvulas.ClienteValvula;
import com.sysco.api.georeferencia.app.dto.valvulas.FiltroClientesValvulasRequest;
import com.sysco.api.georeferencia.app.excepciones.RepositorioExcepcion;
import com.sysco.api.georeferencia.app.interfaces.valvulas.IClientesValvulas;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class ClientesValvulasRepositorio extends IGenericRepo implements IClientesValvulas {

    @Override
    public List<ClienteValvula> listarClientesValvulas(FiltroClientesValvulasRequest filtro) {
        try {
            // La función tiene dos versiones (codcliente integer y text): los tipos explícitos eligen la de integer.
            String query = "SELECT * FROM usp_sysco_clientes_valvulas(?::text, ?::text, ?::integer, ?::double precision)";

            return this.jTemplateGIS().query(query,
                    new BeanPropertyRowMapper<>(ClienteValvula.class),
                    new SqlParameterValue(Types.VARCHAR, filtro.getSector()),
                    new SqlParameterValue(Types.VARCHAR, filtro.getSubsector()),
                    new SqlParameterValue(Types.INTEGER, filtro.getCodcliente()),
                    new SqlParameterValue(Types.DOUBLE, filtro.getDistanciametros()));

        } catch (Exception ex) {
            throw new RepositorioExcepcion(ex.getMessage(), ex);
        }
    }
}
