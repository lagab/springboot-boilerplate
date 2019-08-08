package com.lagab.boilerplate.store.impl;

import com.lagab.boilerplate.config.StorageProperties;
import com.lagab.boilerplate.errors.SystemException;
import com.lagab.boilerplate.utils.StringConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author gabriel
 * @since 02/08/2019.
 */
public class FileSystemStore extends BaseStore {

    public FileSystemStore(StorageProperties storeConfig) {
        super(storeConfig);
    }

    protected File getDirNameDir(String path) {
        return Paths.get(path).toFile();
    }

    @Override
    public void addDirectory(String path) throws SystemException {
        File dirNameDir = getDirNameDir(path);
        if (dirNameDir.exists()) {
            return;
        }
        try {
            FileUtils.forceMkdir(dirNameDir);
        }
        catch (IOException ioe) {
            throw new SystemException(ioe);
        }
        log.debug("add directory " + path);
    }

    @Override
    public void addFile(String path, InputStream is) throws SystemException {
        File targetFile = Paths.get(path).toFile();
        try {
            FileUtils.copyInputStreamToFile(is, targetFile);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        log.debug("add file " + path);
    }

    @Override
    public File getFile(String path) throws SystemException {
        return Paths.get(path).toFile();
    }

    @Override
    public InputStream getFileAsStream(String path) throws SystemException {
        File file = getFile(path);
        try {
            return  FileUtils.openInputStream(file);
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public String[] getFileNames(String path) {
        File[] files = Paths.get(path).toFile().listFiles();
        if( files == null || files.length == 0){
            return new String[0];
        }
        return Arrays.stream(files).map(File::getName).toArray(String[]::new);
    }

    @Override
    public long getFileSize(String path) {
        return FileUtils.sizeOf(getDirNameDir(path));
    }

    @Override
    public boolean hasDirectory(String path) {
        return Files.isDirectory(Paths.get(path));
    }

    @Override
    public boolean hasFile(String path) {
        return Files.isRegularFile(Paths.get(path));
    }

    @Override
    public void move(String srcDir, String destDir) throws SystemException {
        File sourceFile = Paths.get(getPath(srcDir)).toFile();
        File targetFile = Paths.get(getPath(destDir)).toFile();
        try {
            FileUtils.moveDirectory(sourceFile,targetFile);
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public void move(String srcDir, String destDir, String fileName) throws SystemException {
        File sourceFile = Paths.get(getPath(srcDir,false),fileName).toFile();
        File targetFile = Paths.get(getPath(destDir,false)).toFile();
        try {
            FileUtils.moveFileToDirectory(sourceFile,targetFile,true);
            if(isEmptyDirectory( getPath(srcDir, false) )) {
                FileUtils.deleteDirectory(new File(getPath(srcDir, false)));
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public void updateFile(String path, InputStream is) throws SystemException {
        File file = getDirNameDir(getPath(path));
        try {
            FileUtils.copyInputStreamToFile(is,file);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        log.debug("update file " + path);
    }

    @Override
    public void deleteDirectory(String path) throws SystemException {
        if( Files.isDirectory(Paths.get(path))){
            try {
                FileUtils.deleteDirectory(new File(path));
            } catch (IOException e) {
                throw new SystemException(e);
            }
        }
    }

    @Override
    public void deleteFile(String path) throws SystemException {
        File targetFile = Paths.get(path).toFile();
        FileUtils.deleteQuietly(targetFile);
        if(isEmptyDirectory( getPath(path) )) {
            try {
                FileUtils.deleteDirectory(new File(getPath(path)));
            } catch (IOException e) {
                throw new SystemException(e);
            }
        }
    }


    public static boolean isEmptyDirectory(String dirName){
        return (!Files.isDirectory(Paths.get(dirName)))
                || Paths.get(dirName).toFile()== null
                ||  Paths.get(dirName).toFile().listFiles().length == 0;
    }

}
