package com.angeldevs.taskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TaskEventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskEventServiceApplication.class, args);
    }
}
