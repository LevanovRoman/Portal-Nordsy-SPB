package com.myapp.downloadfiles.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface DocumentService {

    String uploadDocument(String path, MultipartFile file) throws IOException;

    InputStream getResourceDocument(String path, String name) throws FileNotFoundException;
}
