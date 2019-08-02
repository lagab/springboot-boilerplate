package com.lagab.boilerplate.store.errors;


import com.lagab.boilerplate.errors.SystemException;

/**
 * @author gabriel
 * @since 17/04/2019.
 */
public class NoSuchFileException extends SystemException{
    public NoSuchFileException() {
    }

    public NoSuchFileException(String message) {
        super(message);
    }

    public NoSuchFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
