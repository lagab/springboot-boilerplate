package com.lagab.boilerplate.jpa.domain.dl;

import com.fasterxml.uuid.Generators;
import com.lagab.boilerplate.jpa.domain.audit.AbstractAuditingEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author gabriel
 * @since 21/11/2018.
 */
@Entity
@Table(name = "dl_file")
public class DLFile extends AbstractAuditingEntity implements Serializable {


    private String uuid;

    private  DLFolder folder;

    private String name;

    private String mimeType;

    private String fileName;

    private String description;

    private Long size;

    private Integer readCount;

    private String path;

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

    @ManyToOne(targetEntity = DLFolder.class)
    @JoinColumn(name = "folder_id",nullable = false)
    public DLFolder getFolder() {
        return folder;
    }

    public void setFolder(DLFolder folder) {
        this.folder = folder;
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "mimeType")
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Column(name = "fileName",nullable = false)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "size")
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Column(name = "readCount")
    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    @Column(name = "path",nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
