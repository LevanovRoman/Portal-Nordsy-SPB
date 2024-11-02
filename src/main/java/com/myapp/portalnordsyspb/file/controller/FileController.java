package com.myapp.portalnordsyspb.file.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.file.service.FileService;
import com.myapp.portalnordsyspb.library.controller.MediaTypeUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/file/")
@Tag(name = "File upload & download", description = "Description for file")
@RequiredArgsConstructor
public class FileController {

    private final ServletContext servletContext;

    @Value("${project.filePathDocker.Photo}")
    private String pathPhoto;

    @Value("${project.filePathDocker.Library}")
    private String pathLibrary;

    @Value("${project.filePathDocker.Document}")
    private String pathDocument;

    @RequestMapping("/photo/{fileName}")
    public ResponseEntity<InputStreamResource> serveFileHandlerPhoto(@PathVariable String fileName) throws IOException {
        return getFileByPathAndName(pathPhoto, fileName);
    }

    @RequestMapping("/library/{fileName}")
    public ResponseEntity<InputStreamResource> serveFileHandlerLibrary(@PathVariable String fileName) throws IOException {
        return getFileByPathAndName(pathLibrary, fileName);
    }

    private ResponseEntity<InputStreamResource> getFileByPathAndName(String path, String fileName) throws IOException {
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        File file = new File(path + "/" + fileName);
        String encodedFilename = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + encodedFilename)
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }
}

