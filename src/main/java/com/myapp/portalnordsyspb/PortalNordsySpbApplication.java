package com.myapp.portalnordsyspb;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PortalNordsySpbApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalNordsySpbApplication.class, args);
    }

    @Bean
    public Dotenv dotenv() {
        // Загружаем .env файл из корневой директории
        return Dotenv.configure().filename(".env").load();
    }

}
