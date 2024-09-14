package com.lottus.todo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {
        "com.lottus.todo.core.service", "com.lottus.todo.core.security.service", "com.lottus.todo.core.controller",
        "com.lottus.todo.core.security.config", "com.lottus.todo.core.config"} )
@EnableJpaRepositories(basePackages = "com.lottus.todo.core.repository")
@EntityScan(basePackages = {"com.lottus.todo.core.domain"})
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class LottusTodoCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(LottusTodoCoreApplication.class, args);
    }
}