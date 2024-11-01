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
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/file/")
@Tag(name = "File upload & download", description = "Description for file")
@RequiredArgsConstructor
public class FileController {

    private final ServletContext servletContext;

    private final FileService fileService;

    @Value("${project.filePathDocker}")
    private String path;
//    private final String path ="/home/photos/";

    @PostMapping("/upload")
    public ResponseEntity<MessageDto> uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadedFileName = fileService.uploadFile(path, file);
        return ResponseEntity.ok(new MessageDto("File uploaded : " + uploadedFileName));
    }

    @RequestMapping("/{fileName}")
    public ResponseEntity<InputStreamResource> serveFileHandler(@PathVariable String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        File file = new File(path + "/" + fileName);
        String encodedFilename = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + encodedFilename)
                // Content-Type
                .contentType(mediaType)
                // Content-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}

