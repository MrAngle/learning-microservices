package com.lippio.notification;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Notification config.
 */
@Configuration
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;
    
    @Value("${rabbitmq.queue.notification}")
    private String notificationQueue;
    
    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;


    /**
     * Internal topic exchange topic exchange.
     *
     * SETTINGS FOR EXCHANGE PART
     *
     * @return the topic exchange
     */
    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }


    /**
     * Notification queue queue.
     *
     * SETTINGS FOR QUEUE
     *
     * @return the queue
     */
    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueue);
    }

    /**
     * Internal to notification binding.
     *
     * BINDING - routing key
     *
     * @return the binding
     */
    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }

    /**
     * Gets internal exchange.
     *
     * @return the internal exchange
     */
    public String getInternalExchange() {
        return internalExchange;
    }

    /**
     * Gets notification queue.
     *
     * @return the notification queue
     */
    public String getNotificationQueue() {
        return notificationQueue;
    }

    /**
     * Gets internal notification routing key.
     *
     * @return the internal notification routing key
     */
    public String getInternalNotificationRoutingKey() {
        return internalNotificationRoutingKey;
    }

}
