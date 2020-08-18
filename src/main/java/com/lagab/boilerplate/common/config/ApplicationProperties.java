package com.lagab.boilerplate.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(
        prefix = "application",
        ignoreUnknownFields = true
)

public class ApplicationProperties {

    private final ApplicationProperties.Mail mail = new ApplicationProperties.Mail();
    private final ApplicationProperties.Security security = new ApplicationProperties.Security();

    @Getter
    @Setter
    public static class Mail {
        private boolean enabled = false;
        private String from = "";
        private String baseUrl = "";
    }
    @Getter
    @Setter
    public static class Security {
        private final ApplicationProperties.Security.Authentication authentication = new ApplicationProperties.Security.Authentication();

        @Getter
        @Setter
        public static class Authentication {
            private final ApplicationProperties.Security.Authentication.Jwt jwt = new ApplicationProperties.Security.Authentication.Jwt();


            @Getter
            @Setter
            public static class Jwt {
                private String secret;
                private String base64Secret;
                private long tokenValidityInSeconds;
                private long tokenValidityInSecondsForRememberMe;
            }

        }
    }

}
