package com.lippio.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Rabbit mq config.
 */
@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;


    /**
     * Amqp template amqp template.
     *
     * USED TO SEND MESSAGES to QUEUE
     *
     * @return the amqp template
     */
    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }

    /**
     * Simple rabbit listener container factory simple rabbit listener container factory.
     *
     * RECEIVE MESSAGE FROM THE QUEUE
     *
     * @return the simple rabbit listener container factory
     */
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }

    /**
     * Jackson converter message converter.
     *
     * @return the message converter
     */
    @Bean
    public MessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
