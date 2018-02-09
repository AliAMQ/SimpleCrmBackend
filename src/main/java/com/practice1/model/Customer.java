package com.practice1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alireza on 1/4/2018.
 */
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private long id;

    public long getId(){
        return this.id;
    }

    @Column(name = "name", nullable = false)
    @Size(max = 50)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    //@ManyToMany

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "store_customer",
        joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    private Set<Store> stores = new HashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
