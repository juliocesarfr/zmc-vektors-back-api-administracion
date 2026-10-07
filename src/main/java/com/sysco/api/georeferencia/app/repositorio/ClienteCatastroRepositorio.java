package com.sysco.api.georeferencia.app.repositorio;

import com.sysco.api.georeferencia.app.config.IGenericRepo;
import com.sysco.api.georeferencia.app.dto.catastro.BuscarClienteCatastroRequest;
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
}
