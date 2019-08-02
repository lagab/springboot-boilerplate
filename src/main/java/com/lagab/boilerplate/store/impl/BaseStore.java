package com.lagab.boilerplate.store.impl;

import com.lagab.boilerplate.config.StorageProperties;
import com.lagab.boilerplate.errors.SystemException;
import com.lagab.boilerplate.store.Store;
import com.lagab.boilerplate.store.errors.NoSuchFileException;
import com.lagab.boilerplate.utils.StringConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author gabriel
 * @since 01/08/2019.
 */
public abstract class BaseStore implements Store{

    protected final Logger log = LoggerFactory.getLogger(BaseStore.class);
    protected StorageProperties config;

    public BaseStore(StorageProperties storeConfig){
        this.config = storeConfig;
    }

    @Override
    public StorageProperties getConfig() {
        return config;
    }

    @Override
    public void setConfig(StorageProperties config) {
        this.config = config;
    }

    @Override
    public String getPath(String path) {
        return getPath(path,false);
    }

    @Override
    public String getPath(String path, boolean isRelative) {
        return null;
    }

    @Override
    public String getTempPath() {
        return config.getTmpDir() ;
    }

    @Override
    public String getTempPath(String path) {
        return getTempPath() + StringConstants.SLASH +  path;
    }

    @Override
    public String getFileNameDir(String path, String fileName) {
        return path + StringConstants.SLASH + fileName;
    }

    @Override
    public abstract void addDirectory(String path) throws SystemException;

    @Override
    public void addFile(String path, byte[] bytes) throws SystemException {
        addFile(path, new ByteArrayInputStream(bytes));
    }

    @Override
    public void addFile(String path, File file) throws SystemException {
        InputStream is;

        try {
            is = new FileInputStream(file);
            addFile(path, is);
        }
        catch (FileNotFoundException fnfe) {
            throw new NoSuchFileException(file.getName(),fnfe);
        }
    }

    @Override
    public abstract void addFile(String path, InputStream is) throws SystemException;

    @Override
    public abstract File getFile(String path) throws SystemException;

    @Override
    public byte[] getFileAsBytes(String path) throws SystemException {
        byte[] bytes;

        try {
            InputStream is = getFileAsStream(path);
            bytes = IOUtils.toByteArray(is);
        }
        catch (IOException ioe) {
            throw new SystemException(ioe);
        }

        return bytes;
    }

    @Override
    public abstract InputStream getFileAsStream(String path) throws SystemException;

    @Override
    public abstract String[] getFileNames(String path);

    @Override
    public abstract long getFileSize(String path);

    @Override
    public abstract boolean hasDirectory(String path);

    @Override
    public abstract boolean hasFile(String path);

    @Override
    public abstract void move(String srcDir, String destDir) throws SystemException;

    @Override
    public abstract void move(String srcDir, String destDir, String fileName) throws SystemException;

    @Override
    public void updateFile(String path, byte[] bytes) throws SystemException{
        updateFile(path,new ByteArrayInputStream(bytes));
    }

    @Override
    public  void updateFile(String path, File file) throws SystemException{
        try {
            updateFile(path, FileUtils.openInputStream(file));
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public abstract void updateFile(String path, InputStream is) throws SystemException;

    @Override
    public abstract void deleteDirectory(String path) throws SystemException;

    @Override
    public abstract void deleteFile(String path) throws SystemException;
}
