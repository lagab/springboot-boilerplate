package com.lagab.boilerplate.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    //TODO to implements

    void addDirectory(String dirName) throws IOException;
    /*void addDirectory(long repositoryId, String dirName);
    void addFile(long repositoryId, String fileName, MultipartFile file);
    void addFile(long repositoryId, String fileName, byte[] bytes);
    void addFile(long repositoryId, String fileName, File file);
    void addFile(long repositoryId, String fileName, InputStream is);
    void deleteDirectory(long repositoryId, String dirName);
    void deleteFile(long repositoryId, String fileName);
    File getFile(long repositoryId, String fileName);
    byte[] getFileAsBytes(long repositoryId, String fileName);
    InputStream getFileAsStream(long repositoryId, String fileName);
    String[] getFileNames(long repositoryId);
    String[] getFileNames(long repositoryId, String dirName);
    long getFileSize(long repositoryId, String fileName);
    boolean hasDirectory(long repositoryId, String dirName);

    void updateFile(long repositoryId, String fileName,String newFileName);

    void updateFile(long repositoryId, String fileName,MultipartFile file);

    void updateFile(long repositoryId, String fileName, byte[] bytes);

    void updateFile(long repositoryId, String fileName, File file);

    void updateFile(long repositoryId, String fileName, InputStream is);*/

}
