package com.lagab.boilerplate.service;

import com.lagab.boilerplate.jpa.domain.dl.DLFile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author gabriel
 * @since 27/11/2018.
 */
public interface DLFileService {

    DLFile addFile(long userId, long repositoryId, long folderId,
                   String sourceFileName,String mimeType,
                   String title, String description, byte[] bytes);

    DLFile addFile(long userId, long repositoryId, long folderId,
                   String sourceFileName,String mimeType,
                   String title, String description, java.io.File file);

    DLFile addFile(long userId, long repositoryId, long folderId,
                   String sourceFileName,String mimeType,
                   String title, String description,  java.io.InputStream is, long size);

    InputStream getFileAsStream(long fileEntryId);

    InputStream getFileAsStream(String uuid);

    List<DLFile> getFiles(long folderId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    int getFileEntriesCount(long folderId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    int getFileEntriesCount(long folderId, String[] mimeTypes);

    DLFile getFile(long fileId);

    DLFile getFileByUuid(String uuid);

    DLFile moveFile(long fileId, long newFolderId);

    DLFile updateFile(long fileId, String title, String description);

    DLFile updateFile(long fileId, String mimeType, String title, long size, String description, File file);

    DLFile updateFile(long fileId, String mimeType, String title, long size, String description, InputStream inputStream);
}
