package com.lagab.boilerplate.errors;

import org.springframework.security.core.AuthenticationException;

/**
 * @author gabriel
 * @since 15/11/2018.
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}
