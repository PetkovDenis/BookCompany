package ru.petkov.bookcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookCompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookCompanyApplication.class, args);
    }
}
