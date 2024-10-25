package com.myapp.portalnordsyspb;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.HashMap;
import java.util.Map;

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
