package com.lagab.boilerplate.web.rest;

import com.lagab.boilerplate.errors.StorageFileNotFoundException;
import com.lagab.boilerplate.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gabriel
 * @since 16/11/2018.
 */
@RestController
@RequestMapping("/file_requests")
public class FileUploadController {

    private final StorageService storageService;

    private final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public List<String> listUploadedFiles(Model model) throws IOException {
        //storageService.addDirectory("/test-lagab");
       /*model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        return model.toString();*/
       return storageService.loadAll().map(
               path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                       "serveFile", path.getFileName().toString()).build().toString())
               .collect(Collectors.toList());
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
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
