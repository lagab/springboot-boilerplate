package com.lagab.boilerplate.common.web.rest.util;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
        return wrapOrNotFound(maybeResponse, (HttpHeaders) null);
    }

    static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
        return (ResponseEntity) maybeResponse.map((response) -> {
            return ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(header)).body(response);
        }).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Convert a response error to json.
     *
     * @param object the object to convert
     * @return the json converted
     * @throws JsonProcessingException the json exception
     */
    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        return object == null ? null : mapper.writeValueAsString(object);
    }
}
