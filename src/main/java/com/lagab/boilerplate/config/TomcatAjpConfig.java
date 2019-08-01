package com.lagab.boilerplate.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gabriel
 * @since 21/11/2018.
 */
@Configuration
public class TomcatAjpConfig {

    private int port;

    @Autowired
    public TomcatAjpConfig(@Value("${server.tomcat.ajp-port}") int port) {
        this.port = port;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        return server -> {
            if (server != null) {
                server.addAdditionalTomcatConnectors(redirectConnector());
            }
        };
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("AJP/1.3");
        connector.setScheme("http");
        connector.setPort(port);
        connector.setSecure(false);
        connector.setAllowTrace(false);
        return connector;
    }

}
