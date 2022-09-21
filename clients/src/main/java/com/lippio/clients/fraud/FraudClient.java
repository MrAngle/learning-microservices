package com.lippio.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The interface Fraud client.
 */
// other way
//@FeignClient(
//    value = "fraud",
//        path = "api/v1/fraud-check"
//)
@FeignClient("fraud")
public interface FraudClient {

    /**
     * Is fraudster fraud check response.
     *
     * @param customerId the customer id
     * @return the fraud check response
     */
    @GetMapping(path="api/v1/fraud-check/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId);

}
