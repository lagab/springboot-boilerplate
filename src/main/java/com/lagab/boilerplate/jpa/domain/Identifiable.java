package com.lagab.boilerplate.jpa.domain;

/**
 * @author gabriel
 * @since 18/10/2018.
 */
public interface Identifiable<K> {

    K getId();

    void setId(K id);
}
