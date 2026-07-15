package com.sysco.api.georeferencia.app.config;
import org.springframework.stereotype.Component;
import com.zmc.sysco.master.clases.config.obtiene_parametros_config;
import com.zmc.sysco.master.clases.dto.usuario_dto;
import com.zmc.sysco.master.clases.models.paramaeConfig;

@Component
public class ConfigXML {
    private obtiene_parametros_config config;

    public ConfigXML() {
        config = new obtiene_parametros_config();
    }

    public paramaeConfig obtiene_parametros(){
        return config.obtener_parametros();
    }

    public usuario_dto obtiene_usuarios(String tipoaccion) {
        return config.obtiene_usuarios(tipoaccion);
    }
}
