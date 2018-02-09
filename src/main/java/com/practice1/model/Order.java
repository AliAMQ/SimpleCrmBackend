package com.practice1.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Alireza on 2/6/2018.
 */

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private long id;

    @Column(name="number")
    @Size(max = 30)
    private String number;

    @ManyToOne
    private Customer customer;

    public Long getId(){
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
