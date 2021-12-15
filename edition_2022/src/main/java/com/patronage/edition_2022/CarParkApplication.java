package com.patronage.edition_2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.patronage.edition_2022.service", "com.patronage.edition_2022.controller"})
@EntityScan("com.patronage.edition_2022.model")
@EnableJpaRepositories("com.patronage.edition_2022.repository")
public class CarParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarParkApplication.class, args);
    }

}
