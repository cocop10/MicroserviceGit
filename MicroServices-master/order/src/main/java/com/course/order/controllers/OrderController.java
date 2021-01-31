package com.course.order.controllers;

import com.course.order.domain.OrderDomain;
import com.course.order.domain.OrderItem;
import com.course.order.repositories.OrderItemRepository;
import com.course.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    public ResponseEntity<OrderDomain> createNewOrder(@RequestBody OrderDomain orderDomain)
    {
        OrderDomain orderSaved = orderRepository.save(orderDomain);

        if (orderSaved == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");

        return new ResponseEntity<OrderDomain>(orderSaved, HttpStatus.CREATED);
    }

    @GetMapping(value = "/orders")
    public List<OrderDomain> getOrderList()
    {
        return orderRepository.findAll();
    }

    @GetMapping(value = "/ordersByIdCart/{idCart}")
    public List<OrderDomain> getOrderByCartIdList(@PathVariable Long idCart)
    {
        return orderRepository.findByCartId(idCart);
    }

    @GetMapping(value = "/order/{id}")
    public Optional<OrderDomain> getOrder(@PathVariable Long id)
    {
        Optional<OrderDomain> orderDomain = orderRepository.findById(id);

        if (orderDomain == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");

        return orderDomain;
    }

}
