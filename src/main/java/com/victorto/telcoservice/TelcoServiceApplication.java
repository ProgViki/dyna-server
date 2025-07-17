package com.victorto.telcoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TelcoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelcoServiceApplication.class, args);
    }
}