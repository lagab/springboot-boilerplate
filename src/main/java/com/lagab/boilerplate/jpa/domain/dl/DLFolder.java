package com.lagab.boilerplate.jpa.domain.dl;

import com.lagab.boilerplate.jpa.domain.audit.AbstractAuditingEntity;
import com.lagab.boilerplate.listeners.UuidListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import com.fasterxml.uuid.Generators;

/**
 * @author gabriel
 * @since 21/11/2018.
 */
@Entity
@Table(name = "dl_folder")
public class DLFolder extends AbstractAuditingEntity implements Serializable {

    private String uuid;

    private DLRepository repository;

    private boolean mountpoint;

    private DLFolder parentFolder;

    private String path;

    private String name;

    private String description;

    @PrePersist void onPrePersist() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        this.uuid = uuid.toString();
    }

    @Column(name = "uuid",nullable = false,unique = true)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @ManyToOne(targetEntity = DLRepository.class)
    @JoinColumn(name = "repository_id",nullable = false)
    public DLRepository getRepository() {
        return repository;
    }

    public void setRepository(DLRepository repository) {
        this.repository = repository;
    }

    @Column(name = "mountPoint")
    public boolean isMountpoint() {
        return mountpoint;
    }

    public void setMountpoint(boolean mountpoint) {
        this.mountpoint = mountpoint;
    }

    @ManyToOne(targetEntity = DLFolder.class)
    @JoinColumn(name = "parentfolder_id",nullable = true)
    public DLFolder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(DLFolder parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Column(name = "path",nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
