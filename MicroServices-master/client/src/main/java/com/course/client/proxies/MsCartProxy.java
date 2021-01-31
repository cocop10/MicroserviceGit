package com.course.client.proxies;

import com.course.client.beans.CartBean;
import com.course.client.beans.CartItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "ms-cart", url = "localhost:8087")
public interface MsCartProxy {


    @PostMapping(value = "/cart")
    public ResponseEntity<CartBean> createNewCart(@RequestBody CartBean cartData);


    @GetMapping(value = "/cart/{id}")
    public Optional<CartBean> getCart(@PathVariable Long id);

    @PostMapping(value = "/remove-item/{id}/{itemId}")
    public ResponseEntity<CartBean> removeProductToCart(@PathVariable Long id, @PathVariable Long itemId);


    @PostMapping(value = "/cart/{id}")
    public ResponseEntity<CartItemBean> addProductToCart(@PathVariable Long id, @RequestBody CartItemBean cartItem);

    @PutMapping(value = "/cart")
    public ResponseEntity<CartBean> updateCart(@RequestBody CartBean cart);


}
