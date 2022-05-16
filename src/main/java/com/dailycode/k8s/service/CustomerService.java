package com.dailycode.k8s.service;

import com.dailycode.k8s.dao.CustomerRepository;
import com.dailycode.k8s.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @CachePut(key = "'allCustomer'", value = "devCache")
    public List<Customer> addCustomer(Customer customer) {
        repository.save(customer);
        log.info("Customer added into database successfully...");
        return repository.findAll();
    }

    @CachePut(value = "devCache", key = "#customer.id")
    public Customer updateCustomer(Customer customer) {
        log.info("Customer updating into database with customerId {}", customer.getId());
        return repository.save(customer);
    }

    @CacheEvict(value = "devCache", key = "#customerId")
    public String deleteCustomer(String customerId) {
        log.info("Deleting the Customer from database with customerId {}", customerId);
        repository.deleteById(customerId);
        return "Deleted customer with Id :" + customerId;
    }

    @Cacheable(key = "'allCustomer'", value = "devCache")
    public List<Customer> getAllCustomers() {
        log.info("Fetching all customers from database ...");
        return repository.findAll();
    }

    @Cacheable(value = "devCache", key = "#customerId")
    public Customer getCustomer(String customerId) {
        log.info("Fetching customer from database by customerId {}", customerId);
        Optional<Customer> customer = repository.findById(customerId);
        if (customer.isPresent()) return customer.get();
        return new Customer();
    }

    @Cacheable(value = "devCache", key = "#username")
    public Customer getCustomerByName(String username) {
        log.info("Fetching customer from database by username {}", username);
        return repository.findByUsername(username);
    }

    @CacheEvict(value = "devCache", key = "'allUser'")
    public String evictAllAuthorsCached() {
        log.info("Evicted All Authors Cached from : devCache");
        return "Evicted All Authors Cached from : devCache";
    }
}
