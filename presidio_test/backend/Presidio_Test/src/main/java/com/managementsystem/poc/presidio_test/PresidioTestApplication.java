package com.managementsystem.poc.presidio_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins="*")
public class PresidioTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresidioTestApplication.class, args);
    }

}
