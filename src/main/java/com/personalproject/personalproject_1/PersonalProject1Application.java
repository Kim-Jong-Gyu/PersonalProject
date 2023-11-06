package com.personalproject.personalproject_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PersonalProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(PersonalProject1Application.class, args);
    }

}
