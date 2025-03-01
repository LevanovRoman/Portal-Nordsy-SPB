package com.myapp.portalnordsyspb;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class PortalNordsySpbApplication {

    private static final Logger logger = LoggerFactory.getLogger(PortalNordsySpbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PortalNordsySpbApplication.class, args);
    }

    @Bean
    public Dotenv dotenv() {
        // Загружаем .env файл из корневой директории
        return Dotenv.configure().filename(".env").load();
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 5 секунд для подключения
        factory.setReadTimeout(5000);    // 5 секунд для чтения ответа
        return new RestTemplate();
    }

    @PostConstruct
    public void redirectConsoleOutputToLog() {
        System.setOut(new PrintStream(System.out) {
            @Override
            public void println(String message) {
                logger.info(message);
            }
        });

        System.setErr(new PrintStream(System.err) {
            @Override
            public void println(String message) {
                logger.error(message);
            }
        });
    }
}
