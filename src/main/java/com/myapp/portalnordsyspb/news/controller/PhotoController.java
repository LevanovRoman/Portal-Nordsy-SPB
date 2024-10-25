package com.myapp.portalnordsyspb.news.controller;

import com.myapp.portalnordsyspb.news.service.PhotoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/photo/")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @Value("${project.photo}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadedFileName = photoService.uploadPhoto(path, file);
        return ResponseEntity.ok("Файл загружен : " + uploadedFileName);
    }

    @GetMapping(value = "/{fileName}")
    public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        InputStream resourceFile = photoService.getResourcePhoto(path, fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}
