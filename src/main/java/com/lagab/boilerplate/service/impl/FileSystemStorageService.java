package com.lagab.boilerplate.service.impl;

import com.lagab.boilerplate.errors.SystemException;
import com.lagab.boilerplate.service.StorageService;
import com.lagab.boilerplate.store.Store;
import com.lagab.boilerplate.store.errors.DuplicateFileException;
import com.lagab.boilerplate.store.validator.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
@Service
public class FileSystemStorageService implements StorageService {

    protected final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final FileValidator fileValidator;

    private  final Store store;

    @Autowired
    public FileSystemStorageService(Store store,FileValidator fileValidator) {
        this.store = store;
        this.fileValidator = fileValidator;
    }

    @Override
    public void addFile(String path, MultipartFile file, String fileName) throws IOException, SystemException {
        this.fileValidator.validateFile(path,file);
        this.addFile(path,file.getInputStream(),fileName);
    }

    @Override
    public void addFile(String path, InputStream is, String fileName) throws SystemException {
        Path uri = Paths.get(path,fileName);
        this.store.addFile(uri.toString(),is);
    }

    @Override
    public void putFile(String path, MultipartFile file, String fileName) throws SystemException, IOException {
        try {
            this.fileValidator.validateFile(path, file);
        } catch (DuplicateFileException dfe){
            // we will update the file
        }
        this.putFile(path,file.getInputStream(),fileName);
    }
    @Override
    public void putFile(String path, InputStream is, String fileName) throws SystemException {
        Path uri = Paths.get(path,fileName);
        if( fileExist(path)){
            this.store.updateFile(uri.toString(),is);
        }else{
            this.store.addFile(uri.toString(),is);
        }
    }

    @Override
    public InputStream loadFile(String path) throws SystemException {
        return this.store.getFileAsStream(path);
    }

    @Override
    public InputStream loadFile(Path path) throws SystemException {
        return this.store.getFileAsStream(path.toString());
    }

    @Override
    public File getFile(String path) throws SystemException {
        return this.store.getFile(path);
    }

    @Override
    public File getFile(Path path) throws SystemException {
        return getFile(path.toString());
    }

    @Override
    public void deleteFile(String path) throws SystemException {
        this.store.deleteFile(path);
    }

    @Override
    public void deleteFiles(String[] paths)  throws SystemException {
        Arrays.stream(paths).forEach(path -> {
            try {
                this.deleteFile(path);
            } catch (SystemException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void deleteDirectory(String path) throws SystemException {
        this.store.deleteDirectory(path);
    }

    @Override
    public long getFileSize(String path) {
        return this.store.getFileSize(path);
    }

    @Override
    public boolean fileExist(String path) throws SystemException {
        File file = this.store.getFile(path);
        return file != null && file.exists() && file.isFile();
    }

    @Override
    public boolean folderExist(String path) throws SystemException {
        File file = this.store.getFile(path);
        return file != null &&  file.exists() && file.isDirectory();
    }

    @Override
    public boolean hasDirectory(String path) {
        return this.store.hasDirectory(path);
    }
}
