package com.microlipin.customer;

import com.lippio.amqp.RabbitMQMessageProducer;
import com.lippio.clients.fraud.FraudCheckResponse;
import com.lippio.clients.fraud.FraudClient;
import com.lippio.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Customer service.
 */
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    /**
     * Register customer.
     *
     * @param request the request
     */
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer);
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );
        FraudCheckResponse fraudster = fraudClient.isFraudster(customer.getId());
        if (fraudster != null && fraudster.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "TRALAA"
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }

}

