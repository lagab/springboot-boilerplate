package com.lagab.boilerplate.audit;

/**
 * @author gabriel
 * @since 02/11/2018.
 * Enum for the different audit actions
 */
public enum EntityAuditAction {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String value;

    EntityAuditAction(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value();
    }
}
