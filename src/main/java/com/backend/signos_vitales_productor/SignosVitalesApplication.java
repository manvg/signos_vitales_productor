package com.backend.signos_vitales_productor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SignosVitalesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SignosVitalesApplication.class, args);
    }
}