package com.dailycode.k8s;

import com.dailycode.k8s.controller.UserController;
import com.dailycode.k8s.dao.CustomerRepository;
import com.dailycode.k8s.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, classes = {UserController.class})})
@Slf4j
@EnableCaching
public class K8sSpringBootDockerApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository repository;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(K8sSpringBootDockerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Invoking runner and activeProfile is {} .....", activeProfile);
        repository.save(Customer.make("Malya", "test123", "test1@gmail.com", "Admin"));
        repository.save(Customer.make("Moksha", "test123", "test2@gmail.com", "View"));
    }
}
