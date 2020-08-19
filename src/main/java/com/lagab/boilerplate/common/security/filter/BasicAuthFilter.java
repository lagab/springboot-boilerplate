package com.lagab.boilerplate.common.security.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.lagab.boilerplate.common.config.ApplicationProperties;

@Component
public class BasicAuthFilter extends GenericFilterBean {

    public static final String BASIC_PREFIX = "Basic ";

    private static final Logger LOG = LoggerFactory.getLogger(BasicAuthFilter.class);

    private ApplicationProperties applicationProperties;

    public BasicAuthFilter(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

    }

    private boolean isValidAuthHeader(String authHeader) {
        // Remove the bearer prefix to get basic
        final String basic = authHeader.substring(BASIC_PREFIX.length());

        String credentials = new String(Base64.getDecoder().decode(basic), Charset.forName("UTF-8"));

        // Credentials are over "username:password" format
        final String[] values = credentials.split(":", 2);

        return !values[0].equals(applicationProperties.getCredentials().getUsername()) || !values[1].equals(
                applicationProperties.getCredentials().getPassword());
    }
}
