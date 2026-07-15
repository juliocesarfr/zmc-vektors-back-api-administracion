package com.sysco.api.georeferencia.app.config;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import com.sysco.api.georeferencia.app.database.DataBaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.xml.sax.SAXException;

import com.zmc.sysco.master.clases.dto.validar_login;
public class IGenericRepo {
    @Autowired
    private DataBaseConfig db;

    public JdbcTemplate jTemplate(validar_login u) throws SAXException, IOException, ParserConfigurationException {
        return new JdbcTemplate(db.masterDataSource(u));
    }

    public JdbcTemplate jTemplateSIINCO(validar_login u) throws SAXException, IOException, ParserConfigurationException {
        return new JdbcTemplate(db.masterDataSourceSIINCO(u));
    }
    public JdbcTemplate jTemplateGIS()
            throws SAXException, IOException, ParserConfigurationException {
        return new JdbcTemplate(db.gisDataSource());
    }
    public HttpEntity<String> recuperaHeaders(String tokens) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", tokens);
        return new HttpEntity<String>(headers);
    }
}
