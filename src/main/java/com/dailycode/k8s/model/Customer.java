package com.dailycode.k8s.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Document
public class Customer {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;

    public static Customer make(String username, String password, String email, String role) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setRole(role);
        return customer;
    }
}