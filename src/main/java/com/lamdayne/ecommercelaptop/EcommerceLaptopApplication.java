package com.lamdayne.ecommercelaptop;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceLaptopApplication {

    public static void main(String[] args) {

        // ⭐ Load file .env từ thư mục project
        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir")) // ép đọc root project
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        // ⭐ Đưa tất cả biến env vào System properties để Spring dùng được
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );

        // ⭐ DEBUG kiểm tra env đã load chưa
        System.out.println("========= TEST ENV =========");
        System.out.println("Project dir = " + System.getProperty("user.dir"));

        System.out.println("EMAIL = " + System.getProperty("EMAIL"));
        System.out.println("PASS_EMAIL = " + System.getProperty("PASS_EMAIL"));
        System.out.println("EMAIL_PASSWORD = " + System.getProperty("EMAIL_PASSWORD"));
        System.out.println("PASSWORD = " + System.getProperty("PASSWORD"));

        System.out.println("============================");

        // ⭐ Run Spring Boot
        SpringApplication.run(EcommerceLaptopApplication.class, args);
    }

}
