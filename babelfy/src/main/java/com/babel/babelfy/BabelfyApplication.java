package com.babel.babelfy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.babel.babelfy")
public class BabelfyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BabelfyApplication.class, args);
    }
}
