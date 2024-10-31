package com.myapp.portalnordsyspb.file.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // получаем имя файла
        String fileName = file.getOriginalFilename();
        // получаем путь к файлу
        String filePath = path + File.separator + fileName;
        // создаем файл
        File newFile = new File(path);
        // копируем файл или загружаем по указанному пути
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
