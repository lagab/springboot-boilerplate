package com.lagab.boilerplate.errors;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
