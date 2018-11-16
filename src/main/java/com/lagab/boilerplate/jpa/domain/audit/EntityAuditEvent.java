package com.lagab.boilerplate.jpa.domain.audit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * @author gabriel
 * @since 02/11/2018.
 */
@Entity
@Table(name = "entity_audit_event")
public class EntityAuditEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long entityId;

    private String entityType;

    private String action;

    private String entityValue;

    private Integer commitVersion;

    private String modifiedBy;

    private Instant modifiedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "entity_id", nullable = false)
    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    @NotNull
    @Size(max = 255)
    @Column(name = "entity_type", length = 255, nullable = false)
    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @NotNull
    @Size(max=20)
    @Column(name = "action", length = 20, nullable = false)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Lob
    @Column(name = "entity_value")
    public String getEntityValue() {
        return entityValue;
    }

    public void setEntityValue(String entityValue) {
        this.entityValue = entityValue;
    }

    @Column(name = "commit_version")
    public Integer getCommitVersion() {
        return commitVersion;
    }

    public void setCommitVersion(Integer commitVersion) {
        this.commitVersion = commitVersion;
    }

    @Size(max = 100)
    @Column(name = "modified_by", length = 100)
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @NotNull
    @Column(name = "modified_date", nullable = false)
    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityAuditEvent entityAuditEvent = (EntityAuditEvent) o;
        return Objects.equals(id, entityAuditEvent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EntityAuditEvent{" +
                "id=" + id +
                ", entityId='" + entityId + "'" +
                ", entityType='" + entityType + "'" +
                ", action='" + action + "'" +
                ", entityValue='" + entityValue + "'" +
                ", commitVersion='" + commitVersion + "'" +
                ", modifiedBy='" + modifiedBy + "'" +
                ", modifiedDate='" + modifiedDate + "'" +
                '}';
    }
}
