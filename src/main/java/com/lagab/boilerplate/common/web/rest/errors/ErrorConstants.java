package com.lagab.boilerplate.common.web.rest.errors;

import java.net.URI;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorConstants {
    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_ACCESS_DENIED = "error.accessDenied";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String ERR_METHOD_NOT_SUPPORTED = "error.methodNotSupported";
    public static final String ERR_INTERNAL_SERVER_ERROR = "error.internalServerError";
    public static final String PROBLEM_BASE_URL = "https://www.springboilerplate.com/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
}
