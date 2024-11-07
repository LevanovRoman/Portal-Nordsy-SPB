package com.myapp.portalnordsyspb.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DatabaseDumpService {

    private final String dbHost = "172.16.15.77";
    private final String dbPort = "5432";
    private final String dbName = "portal";
    private final String dbUser = "portal";
    private final String dbPassword = "portalnew";
    private final String backupDir = "/home/backups/";

    private static final Logger logger = LoggerFactory.getLogger(DatabaseDumpService.class);

    @Scheduled(cron = "0 */5 * * * * ") // Например, каждый час
    public void createDatabaseDump() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dumpFileName = backupDir + "backup_" + timestamp + ".sql";

        // Создаем команду для дампа
//        String command = String.format(
//                "pg_dump -h %s -p %s -U %s -F c -b -v -f %s %s",
//                dbHost, dbPort, dbUser, dumpFileName, dbName);
//        String command = "pg_dump -h localhost -p 5432 -U portal -d portal -F p -b -v -f " + dumpFileName;

        String[] command = {
                "pg_dump", // полный путь к pg_dump
                "-h", "localhost",
                "-p", "5432",
                "-U", "portal",
                "-d", "portal",
                "-F", "p",
                "-b",
                "-v",
                "-f" + dumpFileName
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
