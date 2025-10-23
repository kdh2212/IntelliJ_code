package com.example.module4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.example.common.CommonConfig;

@SpringBootApplication
@Import(CommonConfig.class)
public class Module2Application {

    public static void main(String[] args) {
        SpringApplication.run(Module2Application.class, args);
    }
} 