package com.practice1.repository;

import com.practice1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alireza on 1/4/2018.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
