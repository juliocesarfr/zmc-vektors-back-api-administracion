package com.sysco.api.georeferencia.app.database;


import java.io.IOException;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;

import com.sysco.api.georeferencia.app.config.ConfigXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.xml.sax.SAXException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import com.zmc.sysco.master.clases.dto.validar_login;

@Configuration
public class DataBaseConfig {
    @Autowired
    private ConfigXML conf;

    public DataSource masterDataSource(validar_login u) throws SAXException, IOException, ParserConfigurationException {
        String cadena_conexion = "jdbc:jtds:sybase://" + u.getIpservidor() + ":" + u.getPuerto() + "/" + u.getNombre_bd();

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(conf.obtiene_parametros().getClas());
        ds.setUrl(cadena_conexion);
        ds.setUsername(u.getCodusu());
        ds.setPassword(u.getPassword());

        return ds;
    }

    public DataSource masterDataSourceSIINCO(validar_login u) throws SAXException, IOException, ParserConfigurationException {
        String cadena_conexion = "jdbc:jtds:sybase://" + conf.obtiene_parametros().getIpservidorbd() + ":" + conf.obtiene_parametros().getPuertobd() + "/" + conf.obtiene_parametros().getNombrebdcomercial();
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(conf.obtiene_parametros().getClas());
        ds.setUrl(cadena_conexion);
        ds.setUsername(u.getCodusu());
        ds.setPassword(u.getPassword());

        return ds;
    }

    private HikariDataSource gisDS;

    public synchronized DataSource gisDataSource()
            throws SAXException, IOException, ParserConfigurationException {
        if (gisDS == null) {
            var p = conf.obtiene_parametros();
            HikariConfig cfg = new HikariConfig();
            cfg.setJdbcUrl("jdbc:postgresql://" + p.getIpGis() + ":"
                    + p.getPuertoGis() + "/" + p.getNombreBdGis());
            cfg.setUsername(p.getUsuarioGis());
            cfg.setPassword(p.getPasswordGis());
            cfg.setDriverClassName("org.postgresql.Driver");
            cfg.setMaximumPoolSize(5);
            cfg.setMinimumIdle(1);
            cfg.setConnectionTimeout(10000);
            gisDS = new HikariDataSource(cfg);
        }
        return gisDS;
    }
}
