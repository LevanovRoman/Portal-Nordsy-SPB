package com.myapp.portalnordsyspb.file.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.file.service.FileService;
import com.myapp.portalnordsyspb.library.controller.MediaTypeUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/file/")
@Tag(name = "File upload & download", description = "Description for file")
@RequiredArgsConstructor
public class FileController {

    private final ServletContext servletContext;

    private final FileService fileService;

    private final String path ="/home/photos/";

    @PostMapping("/upload")
    public ResponseEntity<MessageDto> uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadedFileName = fileService.uploadFile(path, file);
        return ResponseEntity.ok(new MessageDto("File uploaded : " + uploadedFileName));
    }

    @RequestMapping("/{fileName}")
    public ResponseEntity<InputStreamResource> serveFileHandler(@PathVariable String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        File file = new File(path + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Content-Length
                .contentLength(file.length()) //
                .body(resource);
    }
//    @GetMapping(value = "/{fileName}")
//    public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
//        InputStream resourceFile = photoService.getResourcePhoto(path, fileName);
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        StreamUtils.copy(resourceFile, response.getOutputStream());
//    }

}

