package com.microlipin.customer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                // if You set wrong package name it will not create beans (for example controllers)
                "com.microlipin.customer",
                "com.lippio.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.lippio.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}