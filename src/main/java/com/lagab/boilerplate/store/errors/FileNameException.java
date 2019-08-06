package com.lagab.boilerplate.store.errors;

import com.lagab.boilerplate.errors.SystemException;

/**
 * @author gabriel
 * @since 17/04/2019.
 */
public class FileNameException extends SystemException{

    public FileNameException() {
        super();
    }

    public FileNameException(String message) {
        super("FileName Invalid : "+ message);
    }

    public FileNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNameException(Throwable cause) {
        super(cause);
    }
}
