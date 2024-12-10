package com.myapp.portalnordsyspb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class FileCleanupService {

    @Value("${project.db.backupDir}")
    private String directoryPath;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseDumpService.class);

    public void deleteOldestFile() {
        File directory = new File(directoryPath);

        // Проверяем, существует ли директория и содержит ли она файлы
        if (directory.exists() && directory.isDirectory()) {
            // Получаем все файлы из директории
            File[] files = directory.listFiles(File::isFile);

            if (files != null && files.length > 0) {
                // Находим самый старый файл (с минимальным временем последнего изменения)
                File oldestFile = Arrays.stream(files)
                        .min(Comparator.comparingLong(File::lastModified))
                        .orElse(null);

                // Удаляем найденный файл
                if (oldestFile.delete()) {
                    logger.info("The oldest file has been deleted: {}", oldestFile.getName());
                } else {
                    logger.info("The file could not be deleted.");
                }
            } else {
                logger.info("There are no files in the directory.");
            }
        } else {
            logger.info("The directory was not found or it is not a folder.");
        }
    }
}

