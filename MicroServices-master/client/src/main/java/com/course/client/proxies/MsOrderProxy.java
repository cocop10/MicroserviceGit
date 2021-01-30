package com.course.client.proxies;

import com.course.client.beans.CartBean;
import com.course.client.beans.CartItemBean;
import com.course.client.beans.OrderBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-order", url = "localhost:8086")
public interface MsOrderProxy {


    @PostMapping(value = "/order")
    ResponseEntity<OrderBean> createNewOrder(@RequestBody OrderBean orderDomain);


    @GetMapping(value = "/orders")
    public List<OrderBean> getOrderList();

    @GetMapping(value = "/order/{id}")
    public Optional<OrderBean> getOrder(@PathVariable Long id);


    @PostMapping(value = "/order/{id}")
    public ResponseEntity<CartItemBean> addProductToCart(@PathVariable Long id, @RequestBody CartItemBean cartItem);




}


