package com.lippio.notification;

import com.lippio.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                // its set by default (I think)
                "com.lippio.notification",
                // scans beans also from different service (for example, its required to use RabbitMQMessageProducer - instead throw error
                "com.lippio.amqp"
        }
)
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner getCommandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig) {
//        return args -> {
//            producer.publish(
//                    new Person("Ali", 12),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }

//    record Person(String name, int age) {}

}
