package com.lagab.boilerplate.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author gabriel
 * @since 18/10/2018.
 */
@MappedSuperclass
public class IdModel implements Identifiable<Long>, Serializable {

    private Long id;


    @Override
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdModel idModel = (IdModel) o;
        return Objects.equals(id, idModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
