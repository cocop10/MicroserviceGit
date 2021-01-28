package com.course.order.controllers;


import com.course.order.domain.Order;
import com.course.order.domain.OrderItem;
import com.course.order.repositories.OrderItemRepository;
import com.course.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @PostMapping(value = "/order")
    public ResponseEntity<Order> createNewOrder(@RequestBody Order orderData)
    {
        Order order = orderRepository.save(new Order());

        if (order == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new order");
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);


    }

    @GetMapping(value = "/orders")
    public List<Order> listOrders()
    {
        List<Order> ordersList = orderRepository.findAll();
        return ordersList;
    }


    @GetMapping(value = "/order/{id}")
    public Optional<Order> getOrder(@PathVariable Long id){

        Optional<Order> order = orderRepository.findById(id);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Couldn't get order");
        return order;

    }

    @PostMapping(value = "/order/{id}")
    @Transactional
    public ResponseEntity<OrderItem> addOrderItemToOrder(@PathVariable Long id, @RequestBody OrderItem orderItem){

        Order order = orderRepository.getOne(id);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");

        order.addOrderItem(orderItem);

        orderRepository.save(order);

        return new ResponseEntity<OrderItem>(orderItem, HttpStatus.CREATED);

    }


}
