package com.microlipin.customer;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


// ACTUATOR TESTS
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CustomerTest {


}