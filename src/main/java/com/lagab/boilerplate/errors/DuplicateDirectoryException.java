package com.lagab.boilerplate.errors;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
public class DuplicateDirectoryException extends StorageException {

    public DuplicateDirectoryException(String message) {
        super(message);
    }

    public DuplicateDirectoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
