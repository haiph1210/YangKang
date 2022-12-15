package com.YangKang.controller;

import com.YangKang.entity.FileUpload;
import com.YangKang.service.IFileService;

import com.YangKang.utils.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin("*")
@Validated
public class FileController {
    @Autowired
    private IFileService fileService;

    @PostMapping(value = "/image")
    private ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {
        if (!new FileManager().isTypeFileImage(image) ){
            return new ResponseEntity<>("File Must Be Image", HttpStatus.UNPROCESSABLE_ENTITY);
        }
            return new ResponseEntity<String>(fileService.storeFile(image), HttpStatus.OK);
    }

    //Inject file Service here

    @PostMapping("")
    public ResponseEntity<FileUpload> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            //save files to a folder => use a service
            String generatedFileName = fileService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new FileUpload("ok", "upload file successfully", generatedFileName)
            );
            //06a290064eb94a02a58bfeef36002483.png => how to open this file in Web Browser ?
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new FileUpload("ok", exception.getMessage(), "")
            );
        }
    }
    //get image's url
    @GetMapping("/files/{fileName:.+}")
    // /files/06a290064eb94a02a58bfeef36002483.png
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = fileService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }
    //How to load all uploaded files ?
    @GetMapping("")
    public ResponseEntity<FileUpload> getUploadedFiles() {
        try {
            List<String> urls = fileService.loadAll()
                    .map(path -> {
                        //convert fileName to url(send request "readDetailFile")
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new FileUpload("ok", "List files successfully", urls));
        }catch (Exception exception) {
            return ResponseEntity.ok(new
                    FileUpload("failed", "List files failed", new String[] {}));
        }
    }
}
