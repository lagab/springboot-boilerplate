package com.lagab.boilerplate.store.errors;

import com.lagab.boilerplate.errors.SystemException;

/**
 * @author gabriel
 * @since 17/04/2019.
 */
public class InvalidContentTypeException extends SystemException{

    public InvalidContentTypeException() {
        super();
    }

    public InvalidContentTypeException(String contentType) {
        super("Content Type "+ contentType +" are invalid ");
    }

    public InvalidContentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidContentTypeException(Throwable cause) {
        super(cause);
    }
}
