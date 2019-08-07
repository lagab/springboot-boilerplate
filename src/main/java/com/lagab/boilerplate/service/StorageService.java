package com.lagab.boilerplate.service;

import com.lagab.boilerplate.errors.SystemException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
public interface StorageService {

    String GetRepository();
    void setRepository(String repository);

    void addFile(String path,MultipartFile file, String fileName) throws IOException, SystemException;
    void addFile(String path,InputStream is, String fileName) throws SystemException;

    void putFile(String path,MultipartFile file, String fileName) throws SystemException, IOException;
    void putFile(String path,InputStream is, String fileName) throws SystemException;

    InputStream loadFile(String path) throws SystemException;
    InputStream loadFile(Path path) throws SystemException;
    File getFile(String path) throws SystemException;
    File getFile(Path path) throws SystemException;

    void deleteFile(String path) throws SystemException;
    void deleteFiles(String[] paths) throws SystemException;
    void deleteDirectory(String path) throws SystemException;

    long getFileSize(String path);
    boolean fileExist(String path) throws SystemException;
    boolean folderExist(String path) throws SystemException;
    boolean hasDirectory(String path);


}
