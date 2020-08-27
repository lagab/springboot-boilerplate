package com.lagab.boilerplate.common.security.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.lagab.boilerplate.common.config.ApplicationProperties;
import com.lagab.boilerplate.common.web.rest.errors.vm.ErrorVM;
import com.lagab.boilerplate.common.web.rest.util.ResponseUtil;

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
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            // Check that the header contain bearer prefix
            if (authHeader == null || !authHeader.toLowerCase().startsWith(BASIC_PREFIX.toLowerCase())) {

                // The authorization header is not set, send forbidden access
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                ErrorVM responseError = ErrorVM.builder()
                                               .status(HttpStatus.FORBIDDEN)
                                               .message("Authorization is required to access this resource.")
                                               .build();

                LOG.error("Authorization is required to access this resource.");

                response.getWriter().write(ResponseUtil.convertObjectToJson(responseError));

                return;
            }
            // This check is optional (spring security will handle bad credentials later), but done here to customize error message
            if (isValidAuthHeader(authHeader)) {
                // The token was not validated, return an unauthorized http code
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                ErrorVM responseError = ErrorVM.builder()
                                               .status(HttpStatus.UNAUTHORIZED)
                                               .message("You are not authorized to access this resource due to bad credentials.")
                                               .build();

                LOG.error("You are not authorized to access this resource due to bad credentials.");

                response.getWriter().write(ResponseUtil.convertObjectToJson(responseError));

                return;
            }
        }

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
