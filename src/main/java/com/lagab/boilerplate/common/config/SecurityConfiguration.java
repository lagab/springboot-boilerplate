package com.lagab.boilerplate.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import com.lagab.boilerplate.common.security.Authorities;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration {
    //@Configuration
    //@Order(2)
    /*public static class UserJwtSecurityConfiguration extends WebSecurityConfigurerAdapter {
        private final AuthenticationManagerBuilder authenticationManagerBuilder;
        //private final UserDetailsService userDetailsService;
        private final TokenProvider tokenProvider;
        //private final SecurityProblemSupport problemSupport;

        public UserJwtSecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
            this.authenticationManagerBuilder = authenticationManagerBuilder;
            //this.userDetailsService = userDetailsService;
            this.tokenProvider = tokenProvider;
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
               .antMatchers(HttpMethod.OPTIONS, "/**")
               .antMatchers("/swagger-ui/index.html")
               .antMatchers("/test/**");
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .csrf()
                    .disable()
                    //.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilter(corsFilter())
                    .exceptionHandling()
                    .and()
                    .headers()
                    .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
                    .and()
                    .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                    .and()
                    .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
                    .and()
                    .frameOptions()
                    .deny()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/auth/authenticate").permitAll()
                    .antMatchers("/auth/register").permitAll()
                    .antMatchers("/auth/activate").permitAll()
                    .antMatchers("/account/reset-password/init").permitAll()
                    .antMatchers("/account/reset-password/finish").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/management/health").permitAll()
                    .antMatchers("/management/info").permitAll()
                    .antMatchers("/management/prometheus").permitAll()
                    .antMatchers("/management/**").hasAuthority(Authorities.ADMIN)
                    .and()
                    .httpBasic()
                    .and()
                    .apply(securityConfigurerAdapter());
            // @formatter:on
        }

        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            source.registerCorsConfiguration("/**", config);
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/auth/**", config);
            source.registerCorsConfiguration("/management/**", config);
            source.registerCorsConfiguration("/v2/api-docs", config);
            source.registerCorsConfiguration("/browser/**", config);
            return new CorsFilter(source);
        }

        private JWTConfigurer securityConfigurerAdapter() {
            return new JWTConfigurer(tokenProvider);
        }

    }*/

    @Configuration
    @Order(1)
    public static class SystemSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private SecurityProblemSupport problemSupport;

        private ApplicationProperties applicationProperties;

        SystemSecurityConfiguration(ApplicationProperties applicationProperties) {
            this.applicationProperties = applicationProperties;
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
               .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**")
               .antMatchers(HttpMethod.OPTIONS, "/**")
               .antMatchers("/test/**");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("password"))
                .roles(Authorities.USER)
                .and()
                .withUser(applicationProperties.getCredentials().getUsername())
                .password(encoder.encode(applicationProperties.getCredentials().getPassword()))
                .roles(Authorities.USER, Authorities.ADMIN);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .csrf()
                    .disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(problemSupport)
                    .accessDeniedHandler(problemSupport)
                    .and()
                    .headers()
                    .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
                    .and()
                    .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                    .and()
                    .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
                    .and()
                    .frameOptions()
                    .deny()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/v2/api-docs").permitAll()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/auth/authenticate").permitAll()
                    .antMatchers("/auth/register").permitAll()
                    .antMatchers("/auth/activate").permitAll()
                    .antMatchers("/account/reset-password/init").permitAll()
                    .antMatchers("/account/reset-password/finish").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/actuator/health").permitAll()
                    .antMatchers("/actuator/info").permitAll()
                    .antMatchers("/actuator/loggers").hasAuthority(Authorities.ADMIN)
                    .antMatchers("/actuator/prometheus").permitAll()
                    .antMatchers("/management/**").hasAuthority(Authorities.ADMIN)
                    .and()
                    .httpBasic();
            // @formatter:on
        }
    }

}
