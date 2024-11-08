package com.myapp.portalnordsyspb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DatabaseDumpService {

    @Value("${project.db.host}")
    private String dbHost;

    @Value("${project.db.port}")
    private String dbPort;

    @Value("${project.db.name}")
    private String dbName;

    @Value("${project.db.user}")
    private String dbUser;

    @Value("${project.db.password}")
    private String dbPassword;

    @Value("${project.db.backupDir}")
    private String backupDir;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseDumpService.class);

//    @Scheduled(cron = "0 */5 * * * * ") // Например, каждые пять минут
    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Moscow")
    public void createDatabaseDump() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dumpFileName = backupDir + "backup_" + timestamp + ".sql";

        String[] command = {
                "pg_dump", // полный путь к pg_dump
                "-h", dbHost,
                "-p", dbPort,
                "-U", dbUser,
                "-d", dbName,
                "-F", "p",
                "-b",
                "-v",
                "-f", dumpFileName
        };

        // Устанавливаем пароль в переменную окружения
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.environment().put("PGPASSWORD", dbPassword);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Дамп базы данных успешно создан: " + dumpFileName);
            } else {
                System.err.println("Ошибка при создании дампа базы данных.");
            }
        } catch (IOException | InterruptedException e) {
            logger.error("Ошибка при выполнении операции: ", e);
        }
    }
}
