package com.dailycode.k8s.dao;

import com.dailycode.k8s.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByUsername(String username);
}
