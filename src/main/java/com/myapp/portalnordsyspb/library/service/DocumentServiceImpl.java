package com.myapp.portalnordsyspb.library.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DocumentServiceImpl implements DocumentService{

    @Override
    public String uploadDocument(MultipartFile file) throws IOException {
        String path = "/home/photos";
        // get name of the file
        String fileName = file.getOriginalFilename();

        // to get the file path
        String filePath = path + File.separator + fileName;

        // create file object
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        // copy the file or upload file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public InputStream getResourceDocument(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }

    @Override
    public String uploadPhoto(String path, MultipartFile file) {
        return "";
    }
}
