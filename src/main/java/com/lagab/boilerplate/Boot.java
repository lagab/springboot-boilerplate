package com.lagab.boilerplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.InetAddress;

/**
 * @author gabriel
 * @since 15/11/2018.
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class Boot {

    private static final Logger log = LoggerFactory.getLogger(Boot.class);

    public Boot(){
        //Default constructor
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Boot.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port","8080"),
                protocol,
                hostAddress,
                env.getProperty("server.port","8080"),
                env.getActiveProfiles());
    }
}
