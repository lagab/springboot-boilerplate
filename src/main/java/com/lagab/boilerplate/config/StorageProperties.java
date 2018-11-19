package com.lagab.boilerplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
@Configuration
@ConfigurationProperties(prefix ="application.storage",ignoreUnknownFields = true)
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
