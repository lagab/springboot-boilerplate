package com.lagab.boilerplate.store;

import com.lagab.boilerplate.config.StorageProperties;
import com.lagab.boilerplate.errors.SystemException;

import java.io.File;
import java.io.InputStream;

/**
 * @author gabriel
 * @since 01/08/2019.
 */
public interface Store {

    public StorageProperties getConfig();
    public void setConfig(StorageProperties config);

    public String getPath(String path);
    public String getPath(String path,boolean isRelative);

    public String getTempPath();
    public String getTempPath(String path);
    public String getFileNameDir(String path, String fileName);

    /**
    * Adds a directory.
    *
    * @param  path the path of directory
    * @throws SystemException if the directory's information was invalid
    */
    public abstract void addDirectory(String path) throws SystemException;

    /**
    * Adds a file based on a byte array.
    *
    * @param  path the path of file
    * @param  bytes the files's data
    * @throws SystemException if the file's information was invalid
    */
    public void addFile(String path, byte[] bytes) throws SystemException;
    /**
    * Adds a file based on a byte array.
    *
    * @param  path the path of file
    * @param  file the file
    * @throws SystemException if the file's information was invalid
    */
    public void addFile(String path, File file) throws SystemException;
    /**
    * Adds a file based on a byte array.
    *
    * @param  path the path of file
    * @param  is the files's input stream
    * @throws SystemException if the file's information was invalid
    */
    public void addFile(String path, InputStream is) throws SystemException;

    public File getFile(String path) throws SystemException;

    public byte[] getFileAsBytes(String path) throws SystemException;

    public InputStream getFileAsStream(String path) throws SystemException;

    public String[] getFileNames(String path);

    public long getFileSize(String path);

    public boolean hasDirectory(String path);
    public boolean hasFile(String path);

    public void move(String srcDir, String destDir) throws SystemException;
    public void move(String srcDir, String destDir, String fileName) throws SystemException;

    public void updateFile(String path, byte[] bytes) throws SystemException;
    public void updateFile(String path, File file) throws SystemException;
    public void updateFile(String path, InputStream is) throws SystemException;

    public void deleteDirectory(String path) throws SystemException;

    public void deleteFile(String path) throws SystemException;
}
