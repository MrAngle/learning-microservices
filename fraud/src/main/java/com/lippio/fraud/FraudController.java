package com.lippio.fraud;

import com.lippio.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Fraud controller.
 */
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    /**
     * Fraud check response fraud check response.
     *
     * @param customerId the customer id
     * @return the fraud check response
     */
    @GetMapping(path="{customerId}")
    public FraudCheckResponse fraudCheckResponse(@PathVariable("customerId") Long customerId) {
        boolean isfraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);

        return new FraudCheckResponse(isfraudulentCustomer);
    }
}