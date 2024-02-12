package com.giganobs.loadsheddingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LoadSheddingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadSheddingApplication.class, args);
    }

}
