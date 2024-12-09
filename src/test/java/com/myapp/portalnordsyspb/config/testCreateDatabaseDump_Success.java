package com.myapp.portalnordsyspb.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class DatabaseBackupServiceTest {

    @InjectMocks
    private DatabaseDumpService databaseBackupService; // Ваш класс с функцией createDatabaseDump

    @Mock
    private FileCleanupService fileCleanupService; // Зависимость, которую мы мокируем

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Настройки для теста
//        databaseBackupService.backupDir = "/backup/";
//        databaseBackupService.dbHost = "localhost";
//        databaseBackupService.dbPort = "5432";
//        databaseBackupService.dbUser = "test_user";
//        databaseBackupService.dbPassword = "test_password";
//        databaseBackupService.dbName = "test_db";
    }

    @Test
    void testCreateDatabaseDump_Success() throws IOException, InterruptedException {
        // Имитация успешного завершения процесса
        Process mockProcess = mock(Process.class);
        when(mockProcess.waitFor()).thenReturn(0);

        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);
        when(mockProcessBuilder.start()).thenReturn(mockProcess);

        // Spy для контроля ProcessBuilder
        ProcessBuilder spyProcessBuilder = spy(new ProcessBuilder());
        doReturn(mockProcessBuilder).when(spyProcessBuilder).start();

        databaseBackupService.createDatabaseDump();

        // Проверяем, что fileCleanupService.deleteOldestFile() был вызван
        verify(fileCleanupService, times(1)).deleteOldestFile();

        // Проверяем, что команда pg_dump сформирована корректно
        String expectedCommand = "pg_dump -h localhost -p 5432 -U test_user -d test_db -F p -b -v -f /backup/backup";
        verify(mockProcessBuilder.environment(), times(1)).put("PGPASSWORD", "test_password");
    }

    @Test
    void testCreateDatabaseDump_Failure() throws IOException, InterruptedException {
        // Имитация ошибки при выполнении команды
        Process mockProcess = mock(Process.class);
        when(mockProcess.waitFor()).thenReturn(1); // Ненулевой код выхода

        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);
        when(mockProcessBuilder.start()).thenReturn(mockProcess);

        ProcessBuilder spyProcessBuilder = spy(new ProcessBuilder());
        doReturn(mockProcessBuilder).when(spyProcessBuilder).start();

        databaseBackupService.createDatabaseDump();

        // Проверяем, что fileCleanupService.deleteOldestFile() НЕ был вызван
        verify(fileCleanupService, never()).deleteOldestFile();
    }

    @Test
    void testCreateDatabaseDump_ExceptionHandling() throws IOException {
        // Имитация выброса IOException
        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);
        when(mockProcessBuilder.start()).thenThrow(new IOException("Test exception"));

        ProcessBuilder spyProcessBuilder = spy(new ProcessBuilder());
        doReturn(mockProcessBuilder).when(spyProcessBuilder).start();

        databaseBackupService.createDatabaseDump();

        // Проверяем, что исключение было обработано, и файл не удалялся
        verify(fileCleanupService, never()).deleteOldestFile();
    }
}

