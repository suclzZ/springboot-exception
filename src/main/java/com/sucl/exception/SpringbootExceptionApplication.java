package com.sucl.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class SpringbootExceptionApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExceptionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractErrorController> c = applicationContext.getBeansOfType(AbstractErrorController.class);
        System.out.println(c);
    }
}
