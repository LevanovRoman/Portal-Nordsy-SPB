package com.myapp.portalnordsyspb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class FileCleanupService {

    @Value("${project.db.backupDir}")
    private String directoryPath;

//    private final String directoryPath = "/path/to/your/directory";

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
                    System.out.println("Самый старый файл удален: " + oldestFile.getName());
                } else {
                    System.out.println("Не удалось удалить файл.");
                }
            } else {
                System.out.println("В директории нет файлов.");
            }
        } else {
            System.out.println("Директория не найдена или это не папка.");
        }
    }
}

