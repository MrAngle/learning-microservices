package com.microlipin.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);

        List<Object> list = new ArrayList<>();
        paintAllBuildings(list);
        list.add(2);
        list.add(2.1);
        System.out.println(list);
    }

    @GetMapping("/test")
    public void testEndpoint() {
        System.out.println("working fine");
    }

    public void paintAllBuildings(List<? super Number> buildings) {
        System.out.println(buildings);
    }
}
