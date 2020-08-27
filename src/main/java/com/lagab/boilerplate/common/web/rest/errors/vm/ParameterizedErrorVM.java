package com.lagab.boilerplate.common.web.rest.errors.vm;

import java.io.Serializable;

import lombok.Getter;

/**
 * View Model for sending a parameterized error message.
 */
@Getter
public class ParameterizedErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String message;
    private final String[] params;

    public ParameterizedErrorVM(String message, String... params) {
        this.message = message;
        this.params = params;
    }

}