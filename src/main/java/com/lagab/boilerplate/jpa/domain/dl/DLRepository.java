package com.lagab.boilerplate.jpa.domain.dl;


import com.lagab.boilerplate.jpa.domain.IdModel;
import com.lagab.boilerplate.jpa.domain.audit.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author gabriel
 * @since 21/11/2018.
 */
@Entity
@Table(name = "dl_repository")
public class DLRepository extends AbstractAuditingEntity implements Serializable {

    private String name;

    private String description;

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
