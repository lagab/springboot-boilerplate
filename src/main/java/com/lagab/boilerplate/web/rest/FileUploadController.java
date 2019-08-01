package com.lagab.boilerplate.web.rest;

import com.lagab.boilerplate.errors.StorageFileNotFoundException;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * @author gabriel
 * @since 16/11/2018.
 */
@Api(tags = "File Request Controller")
@RestController
@RequestMapping("/file_requests")
public class FileUploadController {

    //private final StorageService storageService;

    private final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    /*private final DLRepositoryRepository repo;
    private final DLFolderRepository repo2;



    @Autowired
    public FileUploadController(StorageService storageService,DLRepositoryRepository repository,DLFolderRepository repository2) {
        this.storageService = storageService;
        this.repo= repository;
        this.repo2 = repository2;
    }*/
/*

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
*/
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        //storageService.store(file);
        log.info("You successfully uploaded " + file.getOriginalFilename() + "!");

        return "You successfully uploaded " + file.getOriginalFilename() + "!";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    /*file_requests:

/create
/get
/list
/update
files
/copy
/copy_batch
/copy_batch/check
/copy_reference/get
/copy_reference/save
/create_folder
/create_folder_batch
/create_folder_batch/check
/delete
/delete_batch
/delete_batch/check
/download
/download_zip
/get_metadata
/get_preview
/get_temporary_link
/get_temporary_upload_link
/get_thumbnail
/get_thumbnail_batch
/list_folder
/list_folder/continue
/list_folder/get_latest_cursor
/list_folder/longpoll
/list_revisions
/move
/move_batch
/move_batch/check
/permanently_delete
/restore
/save_url
/save_url/check_job_status
/search
/upload
/upload_session/append
/upload_session/finish
/upload_session/finish_batch
/upload_session/finish_batch/check
/upload_session/start


     */

}
