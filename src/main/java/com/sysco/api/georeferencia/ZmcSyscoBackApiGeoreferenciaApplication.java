package com.sysco.api.georeferencia;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Documentation Sysco v1.0", version = "1.0", description = "Documentation v1.0"))
public class ZmcSyscoBackApiGeoreferenciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmcSyscoBackApiGeoreferenciaApplication.class, args);
    }

}
