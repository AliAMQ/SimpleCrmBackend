package com.practice1.service;

import com.practice1.model.Customer;
import com.practice1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * Created by Alireza on 1/6/2018.
 */

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<?> addCustomer(@RequestBody @Validated Customer customer){
            Customer customer1 = new Customer();
            customer1.setName(customer.getName());
            customerRepository.save(customer1);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCustomer(@RequestBody @Validated Customer customer){
        Customer customer1 = customerRepository.findOne(customer.getId());
        customer1.setName(customer.getName());
        customerRepository.save(customer1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteCustomer(Long id){
        customerRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public Customer getOneCustomer(Long id){
        return customerRepository.findOne(id) ;
    }

    public Collection<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
