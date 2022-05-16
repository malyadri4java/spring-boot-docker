package com.dailycode.k8s.controller;

import com.dailycode.k8s.model.Customer;
import com.dailycode.k8s.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
//@Profile("prod")
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class.getSimpleName());

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public List<Customer> addNewCustomer(@RequestBody Customer customer) {
        logger.info("adding a new Customer...");
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public Customer updateUser(@PathVariable String customerId, @RequestBody Customer customer) {
        logger.info("updating customer with new set of data...");
        customer.setId(customerId);
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{customerId}")
    public String deleteCustomer(@PathVariable String customerId) {
        logger.info("Deleting customer with userId {}", customerId);
        customerService.deleteCustomer(customerId);
        return "Deleted customer successfully...";
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        logger.info("Fetching all customers from database...");
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getUser(@PathVariable String customerId) {
        logger.info("get customer details by the customerId {}", customerId);
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/by/{username}")
    public Customer getUserByName(@PathVariable String username) {
        logger.info("get customer details by the name {}", username);
        return customerService.getCustomerByName(username);
    }

    @GetMapping("/clear")
    public String evictAllAuthorsCached() {
        return customerService.evictAllAuthorsCached();
    }
}
