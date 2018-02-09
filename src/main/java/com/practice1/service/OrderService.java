package com.practice1.service;

import com.practice1.model.Order;
import com.practice1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * Created by Alireza on 2/6/2018.
 */

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<?> addOrder(@RequestBody @Validated Order order){
            Order order1 = new Order();
            order1.setNumber(order.getNumber());
            order1.setCustomer(order.getCustomer());
            orderRepository.save(order1);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateOrder(@RequestBody @Validated Order order){
        Order order1 = orderRepository.findOne(order.getId());
        order1.setNumber(order.getNumber());
        order1.setCustomer(order.getCustomer());
        orderRepository.save(order1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteOrder(Long id){
        orderRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public Order getOneOrder(Long id){
        return orderRepository.findOne(id) ;
    }

    public Collection<Order> getAllOrders(){
        return orderRepository.findAll();
    }

}
