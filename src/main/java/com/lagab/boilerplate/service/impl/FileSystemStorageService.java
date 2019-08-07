package com.lagab.boilerplate.service.impl;

import com.lagab.boilerplate.errors.SystemException;
import com.lagab.boilerplate.service.StorageService;
import com.lagab.boilerplate.store.Store;
import com.lagab.boilerplate.store.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final FileValidator fileValidator;

    private  final Store store;

    private String repository;

    @Autowired
    public FileSystemStorageService(Store store,FileValidator fileValidator) {
        this.store = store;
        this.fileValidator = fileValidator;
    }

    @Override
    public String GetRepository() {
        return repository;
    }

    @Override
    public void setRepository(String repository) {
        this.repository = repository;
    }

    @Override
    public void addFile(String path, MultipartFile file, String fileName) throws IOException, SystemException {
        fileValidator.validateFile(path,file);
        this.addFile(path,file.getInputStream(),fileName);
    }

    @Override
    public void addFile(String path, InputStream is, String fileName) throws SystemException {
        store.addFile(path,is);
    }

    @Override
    public void putFile(String path, MultipartFile file, String fileName) throws SystemException, IOException {
        fileValidator.validateFile(path,file);
        this.putFile(path,file.getInputStream(),fileName);
    }
    @Override
    public void putFile(String path, InputStream is, String fileName) throws SystemException {
        if( fileExist(path)){
            store.updateFile(path,is);
        }else{
            store.addFile(path,is);
        }
    }

    @Override
    public InputStream loadFile(String path) throws SystemException {
        return store.getFileAsStream(path);
    }

    @Override
    public InputStream loadFile(Path path) throws SystemException {
        return store.getFileAsStream(path.toString());
    }

    @Override
    public File getFile(String path) throws SystemException {
        return store.getFile(path);
    }

    @Override
    public File getFile(Path path) throws SystemException {
        return getFile(path.toString());
    }

    @Override
    public void deleteFile(String path) throws SystemException {
        store.deleteFile(path);
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
        store.deleteDirectory(path);
    }

    @Override
    public long getFileSize(String path) {
        return store.getFileSize(path);
    }

    @Override
    public boolean fileExist(String path) throws SystemException {
        File file = store.getFile(path);
        return file.exists() && file.isFile();
    }

    @Override
    public boolean folderExist(String path) throws SystemException {
        File file = store.getFile(path);
        return file.exists() && file.isDirectory();
    }

    @Override
    public boolean hasDirectory(String path) {
        return store.hasDirectory(path);
    }



    /* @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void addDirectory(String dirName) throws IOException {
        Path dir = Paths.get(this.rootLocation.toString()+dirName);
        boolean dirExists = Files.exists(dir) && Files.isDirectory(dir);
        if(dirExists ){
            throw new DuplicateDirectoryException("Directory already Exist: "+dirName);
        }else{
            Files.createDirectories(dir);
        }
    }
    */
}
