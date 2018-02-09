package com.practice1.controller;

import com.practice1.model.Customer;
import com.practice1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Alireza on 1/6/2018.
 */

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody @Validated Customer customer){
        return customerService.addCustomer(customer);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateCustomer(@RequestBody @Validated Customer customer){
        return customerService.updateCustomer(customer);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> updateCustomer(Long id){
        return customerService.deleteCustomer(id);
    }

    @RequestMapping(value = "/getone", method = RequestMethod.GET)
    public ResponseEntity<?> getOneCustomer(Long id){
        return new ResponseEntity<Customer>(customerService.getOneCustomer(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomer(){
        return new ResponseEntity<Collection<Customer>>(customerService.getAllCustomers(),HttpStatus.OK);
    }

}